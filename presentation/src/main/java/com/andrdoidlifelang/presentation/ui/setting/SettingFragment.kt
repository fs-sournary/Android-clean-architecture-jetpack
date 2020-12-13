package com.andrdoidlifelang.presentation.ui.setting

import androidx.fragment.app.viewModels
import com.andrdoidlifelang.presentation.R
import com.andrdoidlifelang.presentation.databinding.FragmentSettingBinding
import com.andrdoidlifelang.presentation.ui.base.MainNavigationFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingFragment : MainNavigationFragment<FragmentSettingBinding, SettingViewModel>() {

    override val viewModel by viewModels<SettingViewModel>()

    override val layoutId: Int = R.layout.fragment_setting
}
