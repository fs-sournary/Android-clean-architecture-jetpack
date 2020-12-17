package com.andrdoidlifelang.presentation.ui.info

import androidx.fragment.app.viewModels
import com.andrdoidlifelang.presentation.R
import com.andrdoidlifelang.presentation.databinding.FragmentInfoBinding
import com.andrdoidlifelang.presentation.ui.base.BaseMainNavigationFragment
import com.andrdoidlifelang.presentation.util.Constant.ANALYTICS_INFO_SCREEN_NAME
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class InfoFragment : BaseMainNavigationFragment<FragmentInfoBinding, InfoViewModel>() {

    override val viewModel by viewModels<InfoViewModel>()

    override val layoutId: Int = R.layout.fragment_info

    override val screenName: String = ANALYTICS_INFO_SCREEN_NAME
}
