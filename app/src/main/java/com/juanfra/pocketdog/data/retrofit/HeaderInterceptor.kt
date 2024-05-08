package es.estech.myapplication.data.retrofit

import com.juanfra.pocketdog.data.retrofit.DoggoService
import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val peticionOriginal = chain.request()
        val requestBuilder = peticionOriginal.newBuilder()
            .header("x-api-key", DoggoService.API_KEY)
            .header("Content-Type", DoggoService.CONTENT_TYPE)
        val request = requestBuilder.build()
        return chain.proceed(request)
    }

}