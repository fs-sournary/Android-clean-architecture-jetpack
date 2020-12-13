package com.andrdoidlifelang.presentation.ui.home

import androidx.fragment.app.viewModels
import com.andrdoidlifelang.presentation.R
import com.andrdoidlifelang.presentation.databinding.FragmentHomeBinding
import com.andrdoidlifelang.presentation.ui.base.MainNavigationFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : MainNavigationFragment<FragmentHomeBinding, HomeViewModel>() {

    override val viewModel by viewModels<HomeViewModel>()

    override val layoutId: Int = R.layout.fragment_home
}
