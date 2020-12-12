package com.andrdoidlifelang.presentation.ui.info

import androidx.fragment.app.viewModels
import com.andrdoidlifelang.presentation.R
import com.andrdoidlifelang.presentation.databinding.FragmentInfoBinding
import com.androidlifelang.base.ui.BaseFragment

class InfoFragment : BaseFragment<FragmentInfoBinding, InfoViewModel>() {

    override val viewModel by viewModels<InfoViewModel>()

    override val layoutId: Int = R.layout.fragment_info
}
