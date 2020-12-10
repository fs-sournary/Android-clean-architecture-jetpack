package com.andrdoidlifelang.android_clean_architecture_jetpack.ui.schedule

import androidx.fragment.app.viewModels
import com.andrdoidlifelang.android_clean_architecture_jetpack.R
import com.andrdoidlifelang.android_clean_architecture_jetpack.databinding.FragmentScheduleBinding
import com.andrdoidlifelang.android_clean_architecture_jetpack.ui.base.BaseFragment

class ScheduleFragment : BaseFragment<FragmentScheduleBinding, ScheduleViewModel>() {

    override val viewModel by viewModels<ScheduleViewModel>()

    override val layoutId: Int = R.layout.fragment_schedule
}
