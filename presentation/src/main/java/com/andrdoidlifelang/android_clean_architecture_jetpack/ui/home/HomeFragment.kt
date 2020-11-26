package com.andrdoidlifelang.android_clean_architecture_jetpack.ui.home

import androidx.fragment.app.viewModels
import com.andrdoidlifelang.android_clean_architecture_jetpack.R
import com.andrdoidlifelang.android_clean_architecture_jetpack.databinding.FragmentHomeBinding
import com.andrdoidlifelang.android_clean_architecture_jetpack.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {

    override val viewModel by viewModels<HomeViewModel>()

    override val layoutId: Int = R.layout.fragment_home
}
