package com.juanfra.pocketdog.data

import android.content.Context
import com.juanfra.pocketdog.data.models.combate.Resultado
import com.juanfra.pocketdog.data.pesetas.Pesetas
import com.juanfra.pocketdog.data.retrofit.AppDataBase
import es.estech.myapplication.data.models.votes.VoteSend
import es.estech.myapplication.data.retrofit.RetrofitHelper

class Repository(val context: Context) {

    private val retroperros = RetrofitHelper.myService
    val database = AppDataBase.getDatabase(context)
    val poketDao = database.dao()

    suspend fun dameRazas() =  retroperros.allRaces()
    suspend fun dameVotos() = retroperros.yourVotes()
    suspend fun votaRaza(voto: VoteSend) = retroperros.votePhoto(voto)
    suspend fun eliminaRaza(id : Int) = retroperros.eliminarVoto(id)
    suspend fun dameFotoRaza(raza : String) = retroperros.imagenPorRaza(raza)
    suspend fun dameDetalles(imageId : String) = retroperros.detallesImage(imageId)

    suspend fun editPesetas(pesetas: Pesetas) = poketDao.editPesetas(pesetas)
    suspend fun insertarPesetas(pesetas: Pesetas) = poketDao.insertPesetas(pesetas)
    suspend fun insertarLog(resultado: Resultado) = poketDao.insertResultado(resultado)
    fun getPesetas() = poketDao.getPesetas()
    fun getLog() = poketDao.getresultado()

}