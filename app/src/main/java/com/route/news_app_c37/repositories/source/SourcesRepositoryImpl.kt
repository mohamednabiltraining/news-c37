package com.route.news_app_c37.repositories.source

import com.route.news_app_c37.api.model.sourcesResponse.Source
import com.route.news_app_c37.repositoriesContract.source.SourcesRemoteDataSource
import com.route.news_app_c37.repositoriesContract.source.SourcesRepository
import javax.inject.Inject

class SourcesRepositoryImpl @Inject constructor(val remoteDataSource: SourcesRemoteDataSource) :
    SourcesRepository {
    override suspend fun getSourcesByCategoryId(catId: String): List<Source?>? {
        val data = remoteDataSource.getSourceByCategoryId(catId);
        //      localDataSource.cacheSources(data)
        return data
//        if(offline)return localDataSource.getSources()
    }
}