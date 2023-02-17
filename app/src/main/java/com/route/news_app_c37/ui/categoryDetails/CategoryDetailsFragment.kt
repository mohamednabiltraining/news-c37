package com.route.news_app_c37.ui.categoryDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayout
import com.google.gson.Gson
import com.route.news_app_c37.R
import com.route.news_app_c37.api.ApiConstants
import com.route.news_app_c37.api.ApiManager
import com.route.news_app_c37.api.model.sourcesResponse.Source
import com.route.news_app_c37.api.model.sourcesResponse.SourcesResponse
import com.route.news_app_c37.databinding.FragmentDetailsCategoryBinding
import com.route.news_app_c37.ui.categories.Category
import com.route.news_app_c37.ui.news.NewsFragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CategoryDetailsFragment : Fragment() {
    lateinit var viewBinding: FragmentDetailsCategoryBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentDetailsCategoryBinding.inflate(
            inflater, container, false
        )
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadNewsSources();
    }

    fun changeNewsFragment(source: Source) {
        childFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, NewsFragment.getInstance(source))
            .commit()

    }

    private fun loadNewsSources() {
        showLoadingLayout()
        ApiManager
            .getApis()
            .getSources(ApiConstants.apiKey, category.id)
            .enqueue(object : Callback<SourcesResponse> {
                override fun onResponse(
                    call: Call<SourcesResponse>,
                    response: Response<SourcesResponse>
                ) {
                    viewBinding.loadingIndicator.isVisible = false

                    if (response.isSuccessful) {
                        bindSourcesInTabLayout(response.body()?.sources)
                    } else {
                        val gson = Gson()
                        val errorResponse =
                            gson.fromJson(
                                response.errorBody()?.string(),
                                SourcesResponse::class.java
                            );
                        showErrorLayout(errorResponse.message);
                    }
                }

                override fun onFailure(call: Call<SourcesResponse>, t: Throwable) {
                    viewBinding.loadingIndicator.isVisible = false
                    showErrorLayout(t.localizedMessage);
                }
            })
    }

    private fun showLoadingLayout() {
        viewBinding.loadingIndicator.isVisible = true
        viewBinding.errorLayout.isVisible = false
    }

    private fun showErrorLayout(message: String?) {
        viewBinding.errorLayout.isVisible = true
        viewBinding.loadingIndicator.isVisible = false;
        viewBinding.errorMessage.text = message
    }

    fun bindSourcesInTabLayout(sourcesList: List<Source?>?) {
        sourcesList?.forEach { source ->
            val tab = viewBinding.tabLayout.newTab()
            tab.text = source?.name
            tab.tag = source
            viewBinding.tabLayout.addTab(tab)

            (tab.view.layoutParams as LinearLayout.LayoutParams).marginStart = 12
            (tab.view.layoutParams as LinearLayout.LayoutParams).marginEnd = 12
//            val layoutParams = LinearLayout.LayoutParams(tab.view.layoutParams)
//            layoutParams.marginEnd =12
//            layoutParams.marginStart  = 12
//            tab.view.layoutParams = layoutParams

        }
        viewBinding.tabLayout
            .addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabReselected(tab: TabLayout.Tab?) {
                    val source = tab?.tag as Source
                    changeNewsFragment(source)
                }

                override fun onTabSelected(tab: TabLayout.Tab?) {
                    val source = tab?.tag as Source
                    changeNewsFragment(source)
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {

                }
            })
        viewBinding.tabLayout.getTabAt(0)?.select()
    }

    lateinit var category: Category

    companion object {
        fun getInstance(category: Category): CategoryDetailsFragment {
            val fragment = CategoryDetailsFragment()
            fragment.category = category
            return fragment
        }
    }
}