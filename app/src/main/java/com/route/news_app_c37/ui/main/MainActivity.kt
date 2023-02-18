package com.route.news_app_c37.ui.main

import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.route.news_app_c37.R
import com.route.news_app_c37.databinding.ActivityMainBinding
import com.route.news_app_c37.ui.categories.CategoriesFragment
import com.route.news_app_c37.ui.categories.Category
import com.route.news_app_c37.ui.categoryDetails.CategoryDetailsFragment
import com.route.news_app_c37.ui.settings.SettingsFragment


class MainActivity : AppCompatActivity(),
    CategoriesFragment.OnCategoryClickListener {
    override fun onCategoryClick(category: Category) {
        showCategoryDetailsFragment(category)
    }

    lateinit var viewBinding: ActivityMainBinding
    val categoriesFragment = CategoriesFragment()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        // setContentView(viewBinding.root)
        val toggle = ActionBarDrawerToggle(
            this, viewBinding.drawer, viewBinding.toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        viewBinding.drawer.addDrawerListener(toggle)
        toggle.syncState()
        viewBinding.navView.setNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_categories -> {
                    showCategoriesFragment();
                }
                R.id.nav_settings -> {
                    showSettingsFragment()
                }
            }
            viewBinding.drawer.closeDrawers()
            return@setNavigationItemSelectedListener true;
        }

        categoriesFragment.onCategoryClickListener = this
        showCategoriesFragment()
    }

    fun showSettingsFragment() {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, SettingsFragment())
            .commit()
    }

    fun showCategoriesFragment() {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, categoriesFragment)
            .commit()
    }

    fun showCategoryDetailsFragment(category: Category) {
        supportFragmentManager
            .beginTransaction()
            .replace(
                R.id.fragment_container,
                CategoryDetailsFragment.getInstance(category)
            )
            .addToBackStack(null)
            .commit()
    }
}