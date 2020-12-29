package com.andrdoidlifelang.presentation.ui.schedule

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import com.andrdoidlifelang.presentation.ui.base.BaseViewModel

class ScheduleViewModel @ViewModelInject constructor(
    @Assisted private val savedStateHandle: SavedStateHandle
) : BaseViewModel()
