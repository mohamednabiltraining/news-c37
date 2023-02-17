package com.route.news_app_c37.api.model.sourcesResponse

import com.google.gson.annotations.SerializedName

data class Source(

    @field:SerializedName("country")
    val country: String? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("description")
    val description: String? = null,

    @field:SerializedName("language")
    val language: String? = null,

    @field:SerializedName("id")
    val id: String? = null,

    @field:SerializedName("category")
    val category: String? = null,

    @field:SerializedName("url")
    val url: String? = null
)