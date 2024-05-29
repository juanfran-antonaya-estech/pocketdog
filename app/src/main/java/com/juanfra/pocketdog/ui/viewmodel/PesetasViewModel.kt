package com.juanfra.pocketdog.ui.viewmodel

import android.content.Context
import android.widget.Toast
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
import com.juanfra.pocketdog.data.models.combate.Resultado
import com.juanfra.pocketdog.data.pesetas.Pesetas
import es.estech.myapplication.data.models.votes.VoteSend
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class PesetasViewModel(val context: Context) : ViewModel() {
    var repo = Repository(context)

    var pesetas = MutableLiveData<Pesetas>(Pesetas(1500))
    val yourtrio = MutableLiveData<DogTrio>(DogTrio(ArrayList()))
    val enemytrio = MutableLiveData<DogTrio>(DogTrio(ArrayList()))
    val win = MutableLiveData<String>("en combate")
    val actualdoggo = MutableLiveData<Doggo>()
    val actualenemy = MutableLiveData<Doggo>()

    /**
     * Obtiene los perros de tus votos.
     *
     * Esta función realiza una llamada a la API para obtener la lista de perros,
     * luego recorre la lista y obtiene los detalles de cada perro de forma asíncrona.
     * Finalmente, crea una lista de objetos `Doggo` con los detalles de cada perro y la ordena por su salud actual.
     *
     * @see Doggo
     */
    fun logBatalla(resultado: Resultado){
        viewModelScope.launch {
            repo.poketDao.insertResultado(resultado)
        }

    }
    fun insertarPesetas(pesetas: Pesetas){
        viewModelScope.launch {
            repo.insertarPesetas(pesetas)
        }
    }

    val misPesetas = repo.getPesetas()
//    fun obtenerPesetas() : MutableLiveData<List<Pesetas>>{
//        val livepeseta = MutableLiveData<List<Pesetas>>()
//            val pesetas = repo.getPesetas()
//            val ptas = pesetas?.value
//            if (ptas?.size == 0){
//                insertarPesetas(Pesetas(1500))
//            }
//            livepeseta.postValue(pesetas.value)
//
//        return livepeseta
//    }

    fun editPesetas(pesetas: Pesetas){

        viewModelScope.launch {
            repo.poketDao.editPesetas(pesetas)
        }
    }

    fun showcaseenemies() : MutableLiveData<DogTrio>{
        val auxtrio = MutableLiveData<DogTrio>()
        viewModelScope.launch {
            val trio = getDogTrios(arrayListOf("normal"))
            auxtrio.postValue(trio[0])

        }
        return auxtrio
    }

    fun resetBattle(){
        win.value = "en combate"
    }

    fun battleTrio(enemies: DogTrio) {
        enemytrio.value = enemies
    }

    fun nextAlly() {
        if (yourtrio.value?.perros?.isEmpty()!!) {
            win.postValue("perdiste")
        } else {
            val auxlist = yourtrio.value?.perros?.let { ArrayList(it) }
            val doggo = auxlist?.random()
            yourtrio.value = auxlist?.let { DogTrio(it) }
            actualdoggo.value = doggo!!
        }
    }

    fun nextEnemy() {
        if (enemytrio.value?.perros?.isEmpty()!!) {
            win.postValue("ganaste")
        } else {
            val auxlist = enemytrio.value?.perros?.let { ArrayList(it) }
            val doggo = auxlist?.random()
            actualenemy.value = doggo!!

        }
    }

    fun enemyDeath(enemy: Doggo) {
        val auxlist = enemytrio.value?.perros?.let { ArrayList(it) }
        if (auxlist != null) {
            auxlist.remove(enemy)
        }
        enemytrio.value = auxlist?.let { DogTrio(it) }
    }

    fun loadDoggos() {
        viewModelScope.launch {
            val response = repo.dameVotos()
            if (response.code() == 200) {
                val perros = response.body()
                val auxlist = ArrayList<Doggo>()

                if (perros != null) {
                    for (perro in perros) {
                        val response = repo.dameDetalles(perro.image.id)
                        if (response.isSuccessful) {
                            auxlist.add(getDoggo(response.body()!!))
                        }
                    }
                }

                yourtrio.postValue(DogTrio(auxlist.sortedBy { it.actualhealth }))
            }
        }
    }

    /**
     * Devuelve una lista de tríos de perros según las dificultades proporcionadas.
     *
     * @param dificultades Una lista de strings con los niveles de dificultad.
     * @return Una lista de objetos DogTrio.
     */
    suspend fun getDogTrios(dificultades: ArrayList<String>): ArrayList<DogTrio> {
        val trios = ArrayList<DogTrio>()
        for (dificultad in dificultades) {
            when (dificultad) {
                "muy facil" -> {
                    val trio = DogTrio(
                        arrayListOf(
                            getRandomDoggo("comun"),
                            getRandomDoggo("comun"),
                            getRandomDoggo("comun")
                        )
                    )
                    trio.packLevel = "Muy Fácil"
                    trio.packName = "Perretes muy facilones"
                    trios.add(
                        trio
                    )

                }

                "facil" -> {
                    val trio = DogTrio(
                        arrayListOf(
                            getRandomDoggo("comun"),
                            getRandomDoggo("comun"),
                            getRandomDoggo("raro")
                        )
                    )
                    trio.packLevel = "Fácil"
                    trio.packName = "Perretes facilones"
                    trios.add(
                        trio
                    )
                }

                "normal" -> {
                    val trio = DogTrio(
                        arrayListOf(
                            getRandomDoggo("comun"),
                            getRandomDoggo("raro"),
                            getRandomDoggo("raro")
                        )
                    )

                    trio.packLevel = "Normal"
                    trio.packName = "Perretes normalitos"
                    trios.add(
                        trio
                    )
                }

                "dificil" -> {
                    val trio = DogTrio(
                        arrayListOf(
                            getRandomDoggo("epico"),
                            getRandomDoggo("raro"),
                            getRandomDoggo("raro")
                        )
                    )

                    trio.packLevel = "Difícil"
                    trio.packName = "Perretes dificilones"
                    trios.add(
                        trio
                    )
                }

                "muy dificil" -> {
                    val trio = DogTrio(
                        arrayListOf(
                            getRandomDoggo("epico"),
                            getRandomDoggo("epico"),
                            getRandomDoggo("epico")
                        )
                    )

                    trio.packLevel = "Muy Difícil"
                    trio.packName = "Perretes muy dificilones"
                    trios.add(
                        trio
                    )
                }

                "pesadilla" -> {
                    val trio = DogTrio(
                        arrayListOf(
                            getRandomDoggo("legendario"),
                            getRandomDoggo("epico"),
                            getRandomDoggo("epico")
                        )
                    )

                    trio.packLevel = "Pesadilla"
                    trio.packName = "Perretes pesadilla"
                    trios.add(
                        trio
                    )
                }
            }
        }
        return trios
    }


    fun buyDoggo(id: String, ptas: Int) {
        viewModelScope.launch {
            if (ptas <= pesetas.value?.pesetas!! && yourtrio.value?.perros?.size!! < 3) {
                repo.votaRaza(VoteSend(imageId = id, value = 1))
                pesetas.postValue(Pesetas(pesetas.value?.pesetas!! - ptas))
                Thread.sleep(1000)
                loadDoggos()
                Toast.makeText(
                    context,
                    "Gracias por tu compra, sus perros han reiniciado sus estadísticas",
                    Toast.LENGTH_SHORT
                ).show()
            } else if (yourtrio.value?.perros?.size!! == 3) {
                Toast.makeText(context, "Tu inventario ya está lleno (3/3)", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "No tienes suficientes pesetas", Toast.LENGTH_SHORT).show()
            }
        }
    }

    //elimina un perro de tus votos
    fun doggoDeath(doggo: Doggo) {


        viewModelScope.launch {
            val response = repo.dameVotos()
            if (response.code() == 200) {
                val votos = response.body()
                for (voto in votos!!) {
                    if (voto.image.id == doggo.refdog.id)
                        repo.eliminaRaza(voto.id)

                }
            }
        }
        val auxlist = ArrayList(yourtrio.value?.perros)
        auxlist.remove(doggo)
        yourtrio.value = DogTrio(auxlist)
        nextAlly()
    }

    //obtiene un perro aleatorio dependiendo de su rareza
    suspend fun getRandomDoggo(rareza: String): Doggo {
        val asyncRazas = viewModelScope.async {
            var response = repo.dameRazas()
            if (response.isSuccessful) {
                return@async response.body()
            } else {
                return@async null
            }
        }
        asyncRazas.join()
        val razas = asyncRazas.getCompleted()
        val asyncFoto = viewModelScope.async {
            var response = repo.dameFotoRaza(razas?.random()?.id.toString())
            if (response.isSuccessful) {
                return@async response.body()
            } else {
                return@async null
            }
        }
        asyncFoto.join()
        val foto = asyncFoto.getCompleted()
        val detalleAsync = viewModelScope.async {
            var response = repo.dameDetalles(foto?.get(0)?.id ?: "")
            if (response.isSuccessful) {
                return@async response.body()
            } else {
                return@async null
            }
        }
        detalleAsync.join()
        val detalle = detalleAsync.getCompleted()
        val doggo = getDoggo(detalle!!)

        return doggo
        //return if (doggo.rarity.lowercase() == rareza) {
        //    doggo
        //} else {
        //    getRandomDoggo(rareza)
        //}
    }

    //convierte el detalle de una foto en un objeto Doggo (usar para conversiones)
    fun getDoggo(detalle: ImagenPerroDetalle): Doggo {
        val doggo: Doggo = with(detalle.breeds[0].name.lowercase()) {
            when {
                contains("border collie") -> BorderCollie(detalle)
                contains("borzoi") -> Borzoi(detalle)
                contains("chihuahua") -> Chihuahua(detalle)
                contains("chow") -> ChowChow(detalle)
                contains("dane") -> GreatDane(detalle)
                contains("husky") -> Husky(detalle)
                contains("tibetan") -> MastTibet(detalle)
                contains("sanchez") -> PerroSanchez(detalle)
                contains("pug") -> Pug(detalle)
                contains("shar") -> SharPei(detalle)
                contains("shiba") -> Shiba(detalle)
                contains("bernard") -> StBernard(detalle)
                else -> Doggo(detalle)
            }
        }
        return doggo

    }

    class PesetasViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return modelClass.getConstructor(Context::class.java).newInstance(context)
        }
    }
}