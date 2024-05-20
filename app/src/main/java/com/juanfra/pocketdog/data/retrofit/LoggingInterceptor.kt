package com.juanfra.pocketdog.data.retrofit

import okhttp3.Interceptor
import okhttp3.Response

class LoggingInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        // Log the request method and URL
        println("Request: ${request.method} ${request.url}")

        // Log the request headers
        for ((name, value) in request.headers) {
            println("Header: $name: $value")
        }

        // Log the request body (if any)
        val requestBody = request.body
        if (requestBody != null) {
            println("Request body: ${requestBody.contentType()} ${requestBody.contentLength()}")
        }

        // Proceed with the request and get the response
        val response = chain.proceed(request)

        // Log the response code and message
        println("Response: ${response.code} ${response.message}")

        // Log the response headers
        for ((name, value) in response.headers) {
            println("Header: $name: $value")
        }

        // Log the response body (if any)
        val responseBody = response.body
        if (responseBody != null) {
            println("Response body: ${responseBody.contentType()} ${responseBody.contentLength()}")
        }

        return response
    }
}