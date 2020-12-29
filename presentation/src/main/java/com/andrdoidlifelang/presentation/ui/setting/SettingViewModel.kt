package com.andrdoidlifelang.presentation.ui.setting

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import com.andrdoidlifelang.presentation.ui.base.BaseViewModel

class SettingViewModel @ViewModelInject constructor(
    @Assisted private val savedStateHandle: SavedStateHandle
) : BaseViewModel()
