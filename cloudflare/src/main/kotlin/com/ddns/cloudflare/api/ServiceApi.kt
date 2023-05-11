package com.ddns.cloudflare.api

import com.ddns.cloudflare.data.IP
import com.ddns.cloudflare.data.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Url


const val baseUrl: String = "https://api.cloudflare.com/client/v4/"

interface ServiceApi {

    @GET("zones/{zone_identifier}/dns_records")
    suspend fun queryDns(@Path("zone_identifier") zoneId: String): Response<List<Response.Result>>

    @GET
    suspend fun queryIP(@Url url: String = "https://qifu-api.baidubce.com/ip/local/geo/v1/district"): IP

    @PUT("zones/{zone_identifier}/dns_records/{identifier}")
    suspend fun updateIP(
        @Path("zone_identifier") zoneId: String,
        @Path("identifier") id: String,
        @Body body: Response.Result,
    ): Response<Response.Result>

}