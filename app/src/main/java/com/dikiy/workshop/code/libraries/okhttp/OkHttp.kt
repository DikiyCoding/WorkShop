package com.dikiy.workshop.code.libraries.okhttp

import okhttp3.*
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull
import java.io.IOException

// RequestBody
// MediaType
class OkHttp {

    init {
        // Клиент должен быть синглтоном
        val client: okhttp3.OkHttpClient = OkHttpClient()
        // Строим урлу запроса
        val urlFormed = buildUrl("")
        // Строим запрос
        val request = buildRequest(urlFormed)
        // Получение ответа сервера
        getResponse(client, request, true)
    }

    /**
     * Builders
     */
    private fun buildUrl(baseUrl: String) =
            // Java - HttpUrl.parse(baseUrl).newBuilder();
            baseUrl.toHttpUrlOrNull()!!.newBuilder()
                    .addQueryParameter("v", "1.0")
                    .addQueryParameter("user", "vogella")
                    .build()
                    .toString()

    private fun buildRequest(urlFormed: String) =
            Request.Builder()
                    .header("Authorization", "your token")
                    .url(urlFormed)
                    .build()

    /**
     * Responses
     */
    // Получение ответа сервера
    private fun getResponse(okHttpClient: okhttp3.OkHttpClient, request: Request, isAsync: Boolean) {
        if (isAsync) getResponseSynchronously(okHttpClient, request)
        else getResponseAsynchronously(okHttpClient, request)
    }

    // Асинхронное получение ответа сервера
    private fun getResponseAsynchronously(okHttpClient: okhttp3.OkHttpClient, request: Request) {
        okHttpClient.newCall(request).enqueue(object : Callback {
            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful) processResponse(response)
                else throw IOException("Unexpected code $response")
            }

            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }
        })
    }

    // Синхронное получение ответа сервера
    private fun getResponseSynchronously(okHttpClient: okhttp3.OkHttpClient, request: Request) {
        val response = okHttpClient
                .newCall(request)
                .execute()
        processResponse(response)
    }

    private fun processResponse(response: Response) {

    }
}