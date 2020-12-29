package com.andrdoidlifelang.presentation.ui.agenda

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import com.andrdoidlifelang.presentation.ui.base.BaseViewModel

class AgendaViewModel @ViewModelInject constructor(
    @Assisted private val savedStateHandle: SavedStateHandle
) : BaseViewModel()
