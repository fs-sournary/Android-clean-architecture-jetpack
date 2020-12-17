package com.andrdoidlifelang.presentation.ui.setting.detail

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.map
import com.andrdoidlifelang.domain.model.Theme
import com.andrdoidlifelang.domain.result.Event
import com.andrdoidlifelang.domain.result.successOr
import com.andrdoidlifelang.domain.usecase.GetThemeObservableUseCase
import com.andrdoidlifelang.domain.usecase.GetThemeUseCase
import com.andrdoidlifelang.presentation.mapper.map
import com.andrdoidlifelang.presentation.model.ThemeUi
import kotlinx.coroutines.flow.map

class DetailSettingViewModel @ViewModelInject constructor(
    getThemeObservableUseCase: GetThemeObservableUseCase,
    getThemeUseCase: GetThemeUseCase
) : ViewModel() {

    val themeObservable: LiveData<ThemeUi> = getThemeObservableUseCase(Unit)
        .map { it.successOr { Theme.SYSTEM }.map() }
        .asLiveData()

    val currentTheme: LiveData<Event<ThemeUi>> = getThemeUseCase(Unit)
        .map { it.successOr { Theme.SYSTEM }.map() }
        .asLiveData()
        .map { Event(it) }
}
