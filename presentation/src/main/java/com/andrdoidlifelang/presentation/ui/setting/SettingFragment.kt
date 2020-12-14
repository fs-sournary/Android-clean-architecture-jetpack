package com.andrdoidlifelang.presentation.ui.setting

import androidx.fragment.app.viewModels
import com.andrdoidlifelang.presentation.R
import com.andrdoidlifelang.presentation.databinding.FragmentSettingBinding
import com.andrdoidlifelang.presentation.ui.base.BaseMainNavigationFragment
import com.andrdoidlifelang.presentation.util.Constant
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingFragment : BaseMainNavigationFragment<FragmentSettingBinding, SettingViewModel>() {

    override val viewModel by viewModels<SettingViewModel>()

    override val layoutId: Int = R.layout.fragment_setting

    override val screenName: String = Constant.ANALYTICS_SETTING_SCREEN_NAME
}
