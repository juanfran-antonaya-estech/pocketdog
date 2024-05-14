package com.juanfra.pocketdog.data.retrofit

import com.juanfra.pocketdog.data.models.breeds.Breed
import es.estech.myapplication.data.models.catphoto.ImagenPerro
import com.juanfra.pocketdog.data.models.catphoto.ImagenPerroDetalle
import es.estech.myapplication.data.models.votes.VoteDeleteResponse
import es.estech.myapplication.data.models.votes.VoteResponse
import es.estech.myapplication.data.models.votes.VoteSend
import es.estech.myapplication.data.models.votes.Votes
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface DoggoService {


        companion object {
            const val API_KEY = "live_dYaVh0CJZQ0cjScSsdfchIFd1oNKjGTY9rq84Y1n8vIbzEnedrilfXhf4HzK6xQI"
            const val CONTENT_TYPE = "application/json"
        }

        @GET("breeds")
        suspend fun allRaces() : Response<ArrayList<Breed>>

        @GET("votes")
        suspend fun yourVotes() : Response<ArrayList<Votes>>

        @POST("votes")
        suspend fun votePhoto(
            @Body body: VoteSend
        ) : Response<VoteResponse>

        @DELETE("votes/{id}")
        suspend fun eliminarVoto(
            @Path("id") id: Int
        ) : Response<VoteDeleteResponse>

        @GET("images/search")
        suspend fun imagenPorRaza(
            @Query("breed_ids") breed_id: String
        ) : Response<ArrayList<ImagenPerro>>

        @GET("images/{id}")
        suspend fun detallesImage(
            @Path("id") id: String
        ) : Response<ImagenPerroDetalle>


}