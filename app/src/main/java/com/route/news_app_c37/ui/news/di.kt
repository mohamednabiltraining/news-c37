package com.route.news_app_c37.ui.news

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent


@Module
@InstallIn(FragmentComponent::class)
class AdaptersModule {
    @Provides
    fun provideNewsAdapter(): NewsAdapter {
        return NewsAdapter(null)
    }
}