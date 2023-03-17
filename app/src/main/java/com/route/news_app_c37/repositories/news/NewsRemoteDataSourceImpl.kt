package com.route.news_app_c37.repositories.news

import com.route.news_app_c37.api.ApiConstants
import com.route.news_app_c37.api.WebServices
import com.route.news_app_c37.api.model.newsResponse.News
import com.route.news_app_c37.repositoriesContract.news.NewsRemoteDataSource

class NewsRemoteDataSourceImpl(val webServices: WebServices) : NewsRemoteDataSource {

    override suspend fun getNewsBySourceId(sourceId: String): List<News?>? {
        val response = webServices.getNews(ApiConstants.apiKey, sourceId)
        return response.articles
    }
}