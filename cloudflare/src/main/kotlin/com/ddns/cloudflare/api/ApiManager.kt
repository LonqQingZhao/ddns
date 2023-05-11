package com.ddns.cloudflare.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiManager {

    @JvmStatic
    val api: ServiceApi by lazy {
               Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(
                OkHttpClient.Builder()
                    .addNetworkInterceptor { chain ->
                        var request = chain.request()
                        request = request.newBuilder()
                            .addHeader("X-Auth-Email", email)
                            .addHeader("X-Auth-Key", authKey)
                            .addHeader("Authorization", auth)
                            .addHeader("Content-Type", "application/json")
                            .build()
                        info("request url:${request.url()}")
                        info("request method:${request.method()}")
                        info("request headers:${request.headers().toMultimap().entries.joinToString(",")}")
                        chain.proceed(request)
                    }
                    .build()
            )
            .build()
            .create(ServiceApi::class.java)
    }

}
