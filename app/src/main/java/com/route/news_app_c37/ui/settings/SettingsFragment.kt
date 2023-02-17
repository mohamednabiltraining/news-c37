package com.route.news_app_c37.ui.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.route.news_app_c37.databinding.FragmentSettingsBinding

class SettingsFragment : Fragment() {
    lateinit var viewBinding: FragmentSettingsBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentSettingsBinding.inflate(
            inflater,
            container, false
        )
        return viewBinding.root
    }
}