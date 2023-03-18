package com.route.news_app_c37.repositories.source

import com.route.news_app_c37.repositoriesContract.source.SourcesRemoteDataSource
import com.route.news_app_c37.repositoriesContract.source.SourcesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class SourcesRemoteDataSourceModule {
    @Binds
    abstract fun bindSourcesRemoteDataSource(
        sourcesRemoteDataSourceImpl: SourcesRemoteDataSourceImpl
    ): SourcesRemoteDataSource
}

@Module
@InstallIn(ViewModelComponent::class)
abstract class SourcesRepositoryModule {
    @Binds
    abstract fun bindSourcesRepository(
        sourcesRep: SourcesRepositoryImpl
    ): SourcesRepository
}