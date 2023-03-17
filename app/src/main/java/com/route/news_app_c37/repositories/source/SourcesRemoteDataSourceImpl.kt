package com.route.news_app_c37.repositories.source

import com.route.news_app_c37.api.ApiConstants
import com.route.news_app_c37.api.WebServices
import com.route.news_app_c37.api.model.sourcesResponse.Source
import com.route.news_app_c37.repositoriesContract.source.SourcesRemoteDataSource

class SourcesRemoteDataSourceImpl(val webServices: WebServices) : SourcesRemoteDataSource {
    override suspend fun getSourceByCategoryId(catId: String): List<Source?>? {
        val sourcesResponse = webServices.getSources(
            ApiConstants.apiKey,
            catId
        )
        return sourcesResponse.sources
    }
}