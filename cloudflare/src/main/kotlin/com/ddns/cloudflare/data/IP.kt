package com.ddns.cloudflare.data


import com.google.gson.annotations.SerializedName

data class IP(
    @SerializedName("charge")
    val charge: Boolean,
    @SerializedName("code")
    val code: String,
    @SerializedName("coordsys")
    val coordsys: String,
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("ip")
    val ip: String?,
    @SerializedName("msg")
    val msg: String,
) {
    data class Data(
        @SerializedName("accuracy")
        val accuracy: String,
        @SerializedName("adcode")
        val adcode: String,
        @SerializedName("areacode")
        val areacode: String,
        @SerializedName("asnumber")
        val asnumber: String,
        @SerializedName("city")
        val city: String,
        @SerializedName("continent")
        val continent: String,
        @SerializedName("country")
        val country: String,
        @SerializedName("district")
        val district: String,
        @SerializedName("isp")
        val isp: String,
        @SerializedName("lat")
        val lat: String,
        @SerializedName("lng")
        val lng: String,
        @SerializedName("owner")
        val owner: String,
        @SerializedName("prov")
        val prov: String,
        @SerializedName("radius")
        val radius: String,
        @SerializedName("source")
        val source: String,
        @SerializedName("timezone")
        val timezone: String,
        @SerializedName("zipcode")
        val zipcode: String,
    )
}