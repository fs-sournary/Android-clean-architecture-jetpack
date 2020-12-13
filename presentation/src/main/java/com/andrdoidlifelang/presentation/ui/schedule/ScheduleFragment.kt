package com.andrdoidlifelang.presentation.ui.schedule

import androidx.fragment.app.viewModels
import com.andrdoidlifelang.presentation.R
import com.andrdoidlifelang.presentation.databinding.FragmentScheduleBinding
import com.andrdoidlifelang.presentation.ui.base.MainNavigationFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ScheduleFragment : MainNavigationFragment<FragmentScheduleBinding, ScheduleViewModel>() {

    override val viewModel by viewModels<ScheduleViewModel>()

    override val layoutId: Int = R.layout.fragment_schedule
}
