package com.route.news_app_c37.repositoriesContract.source

import com.route.news_app_c37.api.model.sourcesResponse.Source

interface SourcesRepository {
    suspend fun getSourcesByCategoryId(catId: String): List<Source?>?
}

interface SourcesRemoteDataSource {
    suspend fun getSourceByCategoryId(catId: String): List<Source?>?
}

interface SourcesLocalDataSource {

}