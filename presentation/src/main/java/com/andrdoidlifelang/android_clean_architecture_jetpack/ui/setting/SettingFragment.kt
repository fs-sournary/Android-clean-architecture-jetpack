package com.andrdoidlifelang.android_clean_architecture_jetpack.ui.setting

import androidx.fragment.app.viewModels
import com.andrdoidlifelang.android_clean_architecture_jetpack.R
import com.andrdoidlifelang.android_clean_architecture_jetpack.databinding.FragmentSettingBinding
import com.androidlifelang.base.ui.BaseFragment

class SettingFragment : BaseFragment<FragmentSettingBinding, SettingViewModel>() {

    override val viewModel by viewModels<SettingViewModel>()

    override val layoutId: Int = R.layout.fragment_setting
}
