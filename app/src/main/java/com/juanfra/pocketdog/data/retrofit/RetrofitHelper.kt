package es.estech.myapplication.data.retrofit

import com.juanfra.pocketdog.data.retrofit.DoggoService
import com.juanfra.pocketdog.data.retrofit.LoggingInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {

    private val BASE_URL = "https://api.thedogapi.com/v1/"

    val httpClient = OkHttpClient.Builder().apply {
        interceptors().add(HeaderInterceptor())
        interceptors().add(LoggingInterceptor())
    }.build()
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(httpClient)
        .build()

    val myService by lazy {
        retrofit.create(DoggoService::class.java)
    }

}