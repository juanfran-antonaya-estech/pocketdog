package com.juanfra.pocketdog.ui.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.juanfra.pocketdog.data.Repository
import com.juanfra.pocketdog.data.doggos.DogTrio
import com.juanfra.pocketdog.data.doggos.Doggo
import com.juanfra.pocketdog.data.doggos.specialdoggos.BorderCollie
import com.juanfra.pocketdog.data.doggos.specialdoggos.Borzoi
import com.juanfra.pocketdog.data.doggos.specialdoggos.Chihuahua
import com.juanfra.pocketdog.data.doggos.specialdoggos.ChowChow
import com.juanfra.pocketdog.data.doggos.specialdoggos.GreatDane
import com.juanfra.pocketdog.data.doggos.specialdoggos.Husky
import com.juanfra.pocketdog.data.doggos.specialdoggos.MastTibet
import com.juanfra.pocketdog.data.doggos.specialdoggos.PerroSanchez
import com.juanfra.pocketdog.data.doggos.specialdoggos.Pug
import com.juanfra.pocketdog.data.doggos.specialdoggos.SharPei
import com.juanfra.pocketdog.data.doggos.specialdoggos.Shiba
import com.juanfra.pocketdog.data.doggos.specialdoggos.StBernard
import com.juanfra.pocketdog.data.models.catphoto.ImagenPerroDetalle
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class PesetasViewModel(val context: Context) : ViewModel() {
    var repo = Repository(context)

    val yourtrio = MutableLiveData<DogTrio>(DogTrio(ArrayList()))

    //obtiene los perros de tus votos
    fun loadDoggos() {
        viewModelScope.launch {
            val response = repo.dameVotos()
            if (response.code() == 200) {
                val perros = response.body()
                val auxlist = ArrayList<Doggo>()
                for (perro in perros!!) {
                    val detalleAsync = viewModelScope.async {
                        var response = repo.dameDetalles(perro.id.toString())
                        if (response.isSuccessful) {
                            return@async response.body()
                        } else {
                            return@async null
                        }
                    }
                    val detalle = detalleAsync.await()
                    val doggo = getDoggo(detalle!!)
                    auxlist.add(doggo)
                }
                yourtrio.postValue(DogTrio(auxlist.sortedBy{ it.actualhealth }))
            }
        }
    }

    //elimina un perro de tus votos
    fun doggoDeath(doggo: Doggo) {
        viewModelScope.launch {
            repo.eliminaRaza(doggo.refdog.id.toInt())
        }
        val auxlist = ArrayList(yourtrio.value?.perros)
        auxlist.remove(doggo)
        yourtrio.value = DogTrio(auxlist)
    }

    //obtiene un perro aleatorio dependiendo de su rareza
    fun getRandomDoggo(rareza: String): Doggo {
        val asyncRazas = viewModelScope.async {
            var response = repo.dameRazas()
            if (response.isSuccessful) {
                return@async response.body()
            } else {
                return@async null
            }
        }
        val razas = asyncRazas.getCompleted()
        val asyncFoto = viewModelScope.async {
            var response = repo.dameFotoRaza(razas?.random()?.id.toString())
            if (response.isSuccessful) {
                return@async response.body()
            } else {
                return@async null
            }
        }
        val foto = asyncFoto.getCompleted()
        val detalleAsync = viewModelScope.async {
            var response = repo.dameDetalles(foto?.get(0)?.id ?: "")
            if (response.isSuccessful) {
                return@async response.body()
            } else {
                return@async null
            }
        }
        val detalle = detalleAsync.getCompleted()
        val doggo = getDoggo(detalle!!)

        if (doggo.rarity.lowercase() == rareza) {
            return doggo
        } else {
            return getRandomDoggo(rareza)
        }
    }

    //convierte el detalle de una foto en un objeto Doggo (usar para conversiones)
    fun getDoggo(detalle: ImagenPerroDetalle): Doggo {
        val doggo: Doggo = when (detalle.breeds[0].name.lowercase()) {
            in "border collie" -> BorderCollie(detalle)
            in "borzoi" -> Borzoi(detalle)
            in "chihuahua" -> Chihuahua(detalle)
            in "chow" -> ChowChow(detalle)
            in "dane" -> GreatDane(detalle)
            in "husky" -> Husky(detalle)
            in "tibet" -> MastTibet(detalle)
            in "sanchez" -> PerroSanchez(detalle)
            in "pug" -> Pug(detalle)
            in "shar" -> SharPei(detalle)
            in "shiba" -> Shiba(detalle)
            in "bernard" -> StBernard(detalle)
            else -> Doggo(detalle)
        }

        return doggo

    }

    class PesetasViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return modelClass.getConstructor(Context::class.java).newInstance(context)
        }
    }
}