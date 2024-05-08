package com.juanfra.pocketdog.data

import es.estech.myapplication.data.models.votes.VoteSend
import es.estech.myapplication.data.retrofit.RetrofitHelper

class Repository {
    private val retroperros = RetrofitHelper.myService

    suspend fun dameRazas() =  retroperros.allRaces()
    suspend fun dameVotos() = retroperros.yourVotes()
    suspend fun votaRaza(voto: VoteSend) = retroperros.votePhoto(voto)
    suspend fun eliminaRaza(id : Int) = retroperros.eliminarVoto(id)
    suspend fun dameFotoRaza(raza : String) = retroperros.imagenPorRaza(raza)
    suspend fun dameDetalles(imageId : String) = retroperros.detallesImage(imageId)

}