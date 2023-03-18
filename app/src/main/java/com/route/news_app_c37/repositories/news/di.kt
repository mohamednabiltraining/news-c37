package com.route.news_app_c37.repositories.news

import com.route.news_app_c37.repositoriesContract.news.NewsRemoteDataSource
import com.route.news_app_c37.repositoriesContract.news.NewsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class NewsRepositoryModule {
   @Binds
   abstract fun getNewsRepo(newsRepository: NewsRepositoryImpl)
           : NewsRepository
}

@Module
@InstallIn(ViewModelComponent::class)
abstract class NewsDataSourceModule {
   @Binds
   abstract fun bindNewsRemoteDataSource(
      newsDataSource: NewsRemoteDataSourceImpl
   ): NewsRemoteDataSource
}
