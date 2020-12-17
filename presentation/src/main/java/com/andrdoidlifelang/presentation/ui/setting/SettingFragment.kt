package com.andrdoidlifelang.presentation.ui.setting

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.andrdoidlifelang.presentation.R
import com.andrdoidlifelang.presentation.databinding.FragmentSettingBinding
import com.andrdoidlifelang.presentation.ui.base.BaseMainNavigationFragment
import com.andrdoidlifelang.presentation.ui.setting.detail.DetailSettingFragment
import com.andrdoidlifelang.presentation.util.Constant.ANALYTICS_SETTING_SCREEN_NAME
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingFragment : BaseMainNavigationFragment<FragmentSettingBinding, SettingViewModel>() {

    override val viewModel by viewModels<SettingViewModel>()

    override val layoutId: Int = R.layout.fragment_setting

    override val screenName: String = ANALYTICS_SETTING_SCREEN_NAME

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        childFragmentManager.beginTransaction().apply {
            replace(R.id.fl_setting, DetailSettingFragment.create()).commit()
        }
    }
}
