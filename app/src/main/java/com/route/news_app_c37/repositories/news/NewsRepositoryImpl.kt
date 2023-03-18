package com.route.news_app_c37.repositories.news

import com.route.news_app_c37.api.model.newsResponse.News
import com.route.news_app_c37.repositoriesContract.news.NewsRemoteDataSource
import com.route.news_app_c37.repositoriesContract.news.NewsRepository
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(val dataSource: NewsRemoteDataSource) :
    NewsRepository {
    override suspend fun getNewsBySourceId(sourceId: String): List<News?>? {
        val news = dataSource.getNewsBySourceId(sourceId)
        return news
    }
}