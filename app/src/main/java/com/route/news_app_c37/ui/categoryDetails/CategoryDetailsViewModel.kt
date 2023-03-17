package com.route.news_app_c37.ui.categoryDetails

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.route.news_app_c37.api.ApiManager
import com.route.news_app_c37.api.model.sourcesResponse.Source
import com.route.news_app_c37.api.model.sourcesResponse.SourcesResponse
import com.route.news_app_c37.repositories.source.SourcesRemoteDataSourceImpl
import com.route.news_app_c37.repositories.source.SourcesRepositoryImpl
import com.route.news_app_c37.repositoriesContract.source.SourcesRepository
import kotlinx.coroutines.launch
import retrofit2.HttpException

class CategoryDetailsViewModel : ViewModel() {
    val sourcesLivedata = MutableLiveData<List<Source?>?>()
    val showLoadingLayout = MutableLiveData<Boolean>()
    val showErrorLayout = MutableLiveData<String>()
    val webServices = ApiManager.getApis();
    val sourcesDataSource = SourcesRemoteDataSourceImpl(webServices)
    val sourcesRepository: SourcesRepository =
        SourcesRepositoryImpl(sourcesDataSource)

    fun loadNewsSources(categoryId: String) {
        viewModelScope.launch {
            showLoadingLayout.value = true
            try {
                val sources = sourcesRepository.getSourcesByCategoryId(categoryId)
                showLoadingLayout.value = false
                sourcesLivedata.value = sources
            } catch (t: HttpException) {
                val errorResponse: SourcesResponse =
                    Gson().fromJson(
                        t.response()?.errorBody()?.string(),
                        SourcesResponse::class.java
                    )
                showLoadingLayout.value = false
                showErrorLayout.value = errorResponse.message ?: ""
            } catch (e: Exception) {
                showErrorLayout.value = e.localizedMessage ?: ""

            }
        }

//            .enqueue(object : Callback<SourcesResponse> {
//                override fun onResponse(
//                    call: Call<SourcesResponse>,
//                    response: Response<SourcesResponse>
//                ) {
//                    showLoadingLayout.value = false
//                    // viewBinding.loadingIndicator.isVisible = false
//
//                    if (response.isSuccessful) {
//                        // we have data
//                        sourcesLivedata.value = response.body()?.sources
//                    } else {
//                        val gson = Gson()
//                        val errorResponse =
//                            gson.fromJson(
//                                response.errorBody()?.string(),
//                                SourcesResponse::class.java
//                            );
//                        showErrorLayout.value = errorResponse.message ?: ""
//                    }
//                }
//
//                override fun onFailure(call: Call<SourcesResponse>, t: Throwable) {
//                    showLoadingLayout.value = false
//                    showErrorLayout.value = t.localizedMessage ?: ""
//                }
//            })
    }
}