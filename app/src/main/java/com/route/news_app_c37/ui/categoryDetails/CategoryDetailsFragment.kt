package com.route.news_app_c37.ui.categoryDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.tabs.TabLayout
import com.route.news_app_c37.R
import com.route.news_app_c37.api.model.sourcesResponse.Source
import com.route.news_app_c37.databinding.FragmentDetailsCategoryBinding
import com.route.news_app_c37.ui.categories.Category
import com.route.news_app_c37.ui.news.NewsFragment

class CategoryDetailsFragment : Fragment() {
    lateinit var viewBinding: FragmentDetailsCategoryBinding
    lateinit var viewModel: CategoryDetailsViewModel

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CategoryDetailsViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.loadNewsSources(category.id)
        subscribeToLiveData()
    }

    fun subscribeToLiveData() {
        viewModel.sourcesLivedata.observe(viewLifecycleOwner) {
            bindSourcesInTabLayout(it)
        }
        viewModel.showLoadingLayout.observe(viewLifecycleOwner) { show ->
            if (show)
                showLoadingLayout()
            else hideLoading();
        }

        viewModel.showErrorLayout.observe(viewLifecycleOwner) {
            showErrorLayout(it)
        }
    }

    fun changeNewsFragment(source: Source) {
        childFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, NewsFragment.getInstance(source))
            .commit()

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