package com.ddns.cloudflare.data


import com.google.gson.annotations.SerializedName


data class Response<T>(
    @SerializedName("errors")
    val errors: List<Any>,
    @SerializedName("messages")
    val messages: List<Any>,
    @SerializedName("result")
    val result: T,
    @SerializedName("result_info")
    val resultInfo: ResultInfo,
    @SerializedName("success")
    val success: Boolean,
) {

    data class Result(
        @SerializedName("comment")
        val comment: String? = "",
        @SerializedName("content")
        val content: String? = "",
        @SerializedName("created_on")
        val createdOn: String? = "",
        @SerializedName("id")
        val id: String? = "",
        @SerializedName("locked")
        val locked: Boolean,
        @SerializedName("meta")
        val meta: Meta? = null,
        @SerializedName("modified_on")
        val modifiedOn: String? = "",
        @SerializedName("name")
        val name: String? = "",
        @SerializedName("proxiable")
        val proxiable: Boolean,
        @SerializedName("proxied")
        val proxied: Boolean,
        @SerializedName("tags")
        val tags: List<Any>,
        @SerializedName("ttl")
        val ttl: Int,
        @SerializedName("type")
        val type: String? = "",
        @SerializedName("zone_id")
        val zoneId: String? = "",
        @SerializedName("zone_name")
        val zoneName: String? = "",
    ) {

        data class Meta(
            @SerializedName("auto_added")
            val autoAdded: Boolean,
            @SerializedName("managed_by_apps")
            val managedByApps: Boolean,
            @SerializedName("managed_by_argo_tunnel")
            val managedByArgoTunnel: Boolean,
            @SerializedName("source")
            val source: String,
        )
    }


    data class ResultInfo(
        @SerializedName("count")
        val count: Int,
        @SerializedName("page")
        val page: Int,
        @SerializedName("per_page")
        val perPage: Int,
        @SerializedName("total_count")
        val totalCount: Int,
        @SerializedName("total_pages")
        val totalPages: Int,
    )
}