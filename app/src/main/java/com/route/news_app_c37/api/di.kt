package com.route.news_app_c37.api

import android.util.Log
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
class ApiModule {

    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        val loginInterceptor = HttpLoggingInterceptor {
            Log.e("api", it)
        };
        loginInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return loginInterceptor;
    }

    @Provides
    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Provides
    fun provideGsonObject(): GsonConverterFactory {
        return GsonConverterFactory.create();
    }

    @Provides
    fun provideRetrofit(
        gson: GsonConverterFactory,
        okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://newsapi.org/")
            .addConverterFactory(gson)
            .client(okHttpClient)
            .build()

    }

    @Provides
    fun bindWebServices(
        retrofit: Retrofit
    ): WebServices {
        return retrofit.create(WebServices::class.java)
    }
}