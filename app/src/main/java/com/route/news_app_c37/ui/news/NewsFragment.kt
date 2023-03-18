package com.route.news_app_c37.ui.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.route.news_app_c37.api.model.newsResponse.News
import com.route.news_app_c37.api.model.sourcesResponse.Source
import com.route.news_app_c37.databinding.FragmentNewsBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class NewsFragment : Fragment() {

    companion object {
        fun getInstance(source: Source): NewsFragment {
            val newNewsFragment = NewsFragment()
            newNewsFragment.source = source;
            return newNewsFragment
        }
    }

    lateinit var source: Source


    lateinit var viewBinding: FragmentNewsBinding
    val vieWModel: NewsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentNewsBinding.inflate(
            inflater,
            container, false
        )
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        vieWModel.getNews(source.id ?: "");
        subscribeToLiveData()
    }

    fun subscribeToLiveData() {
        vieWModel.newsList.observe(viewLifecycleOwner) {
            bindNewsList(it)
        }
        vieWModel.showError.observe(viewLifecycleOwner) {
            showErrorLayout(it)
        }
        vieWModel.showLoading.observe(viewLifecycleOwner) {
            if (it) showLoadingLayout()
            else hideLoading()
        }
    }

    @Inject
    lateinit var newsAdapter: NewsAdapter

    private fun initRecyclerView() {
        viewBinding.newsRecycler.adapter = newsAdapter

    }

    private fun bindNewsList(articles: List<News?>?) {
        // show news in recycler view
        viewBinding.loadingIndicator.isVisible = false
        viewBinding.errorLayout.isVisible = false
        newsAdapter.changeData(articles)
    }

    private fun showLoadingLayout() {
        viewBinding.loadingIndicator.isVisible = true
        viewBinding.errorLayout.isVisible = false
    }

    private fun hideLoading() {
        viewBinding.loadingIndicator.isVisible = false
    }

    private fun showErrorLayout(message: String?) {
        viewBinding.errorLayout.isVisible = true
        viewBinding.loadingIndicator.isVisible = false;
        viewBinding.errorMessage.text = message
    }
}