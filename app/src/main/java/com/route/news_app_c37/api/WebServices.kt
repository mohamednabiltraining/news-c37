package com.route.news_app_c37.api

import com.route.news_app_c37.api.model.newsResponse.NewsResponse
import com.route.news_app_c37.api.model.sourcesResponse.SourcesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WebServices {
    //    https://newsapi.org/
    @GET("v2/top-headlines/sources")
    fun getSources(
        @Query("apiKey") apiKey: String,
        @Query("category") category: String
    ): Call<SourcesResponse>

    @GET("v2/everything")
    fun getNews(
        @Query("apiKey") apiKey: String, @Query("sources") sources: String
    ): Call<NewsResponse>

}