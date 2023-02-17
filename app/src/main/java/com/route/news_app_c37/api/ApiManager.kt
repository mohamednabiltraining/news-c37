package com.route.news_app_c37.api

import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiManager {
    companion object {
        private var retrofit: Retrofit? = null;

        @Synchronized
        private fun getInstance(): Retrofit {
            if (retrofit == null) {
                val loginInterceptor = HttpLoggingInterceptor {
                    Log.e("api", it)
                };
                loginInterceptor.level = HttpLoggingInterceptor.Level.BODY
                val okHttpClient = OkHttpClient.Builder()
                    .addInterceptor(loginInterceptor)
                    .build()

                retrofit = Retrofit.Builder()
                    .baseUrl("https://newsapi.org/")
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit!!;
        }

        fun getApis(): WebServices {
            return getInstance().create(WebServices::class.java);
        }

    }

}