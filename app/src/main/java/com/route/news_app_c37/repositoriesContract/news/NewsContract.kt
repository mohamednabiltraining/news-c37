package com.route.news_app_c37.repositoriesContract.news

import com.route.news_app_c37.api.model.newsResponse.News

interface NewsRepository {
    suspend fun getNewsBySourceId(sourceId: String): List<News?>?
}

interface NewsRemoteDataSource {
    suspend fun getNewsBySourceId(sourceId: String): List<News?>?
}