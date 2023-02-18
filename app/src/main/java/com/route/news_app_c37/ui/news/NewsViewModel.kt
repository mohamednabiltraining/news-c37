package com.route.news_app_c37.ui.news

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.route.news_app_c37.api.ApiConstants
import com.route.news_app_c37.api.ApiManager
import com.route.news_app_c37.api.model.newsResponse.News
import com.route.news_app_c37.api.model.newsResponse.NewsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsViewModel : ViewModel() {
    val showLoading = MutableLiveData<Boolean>()
    val showError = MutableLiveData<String>()
    val newsList = MutableLiveData<List<News?>?>()
    fun getNews(sourceId: String) {
        showLoading.value = true

        ApiManager
            .getApis()
            .getNews(ApiConstants.apiKey, sourceId)
            .enqueue(object : Callback<NewsResponse> {
                override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                    showError.value = t.localizedMessage
                }

                override fun onResponse(
                    call: Call<NewsResponse>,
                    response: Response<NewsResponse>
                ) {
                    if (response.isSuccessful) {
                        // we have news to show
                        newsList.value = response.body()?.articles
                        return
                    }
                    val errorResponse = Gson().fromJson(
                        response.errorBody()?.string(), NewsResponse::class.java
                    )
                    showError.value = errorResponse.message ?: ""
                }
            })
    }

}