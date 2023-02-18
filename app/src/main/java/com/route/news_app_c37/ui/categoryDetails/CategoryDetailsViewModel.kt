package com.route.news_app_c37.ui.categoryDetails

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.route.news_app_c37.api.ApiConstants
import com.route.news_app_c37.api.ApiManager
import com.route.news_app_c37.api.model.sourcesResponse.Source
import com.route.news_app_c37.api.model.sourcesResponse.SourcesResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CategoryDetailsViewModel : ViewModel() {
    val sourcesLivedata = MutableLiveData<List<Source?>?>()
    val showLoadingLayout = MutableLiveData<Boolean>()
    val showErrorLayout = MutableLiveData<String>()

    fun loadNewsSources(categoryId: String) {
        showLoadingLayout.value = true
        ApiManager
            .getApis()
            .getSources(ApiConstants.apiKey, categoryId)
            .enqueue(object : Callback<SourcesResponse> {
                override fun onResponse(
                    call: Call<SourcesResponse>,
                    response: Response<SourcesResponse>
                ) {
                    showLoadingLayout.value = false
                    // viewBinding.loadingIndicator.isVisible = false

                    if (response.isSuccessful) {
                        // we have data
                        sourcesLivedata.value = response.body()?.sources
                    } else {
                        val gson = Gson()
                        val errorResponse =
                            gson.fromJson(
                                response.errorBody()?.string(),
                                SourcesResponse::class.java
                            );
                        showErrorLayout.value = errorResponse.message ?: ""
                    }
                }

                override fun onFailure(call: Call<SourcesResponse>, t: Throwable) {
                    showLoadingLayout.value = false
                    showErrorLayout.value = t.localizedMessage ?: ""
                }
            })
    }
}