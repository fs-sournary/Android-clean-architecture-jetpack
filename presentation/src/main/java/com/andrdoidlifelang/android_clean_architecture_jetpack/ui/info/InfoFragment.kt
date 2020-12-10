package com.andrdoidlifelang.android_clean_architecture_jetpack.ui.info

import androidx.fragment.app.viewModels
import com.andrdoidlifelang.android_clean_architecture_jetpack.R
import com.andrdoidlifelang.android_clean_architecture_jetpack.databinding.FragmentInfoBinding
import com.andrdoidlifelang.android_clean_architecture_jetpack.ui.base.BaseFragment

class InfoFragment : BaseFragment<FragmentInfoBinding, InfoViewModel>() {

    override val viewModel by viewModels<InfoViewModel>()

    override val layoutId: Int = R.layout.fragment_info
}
