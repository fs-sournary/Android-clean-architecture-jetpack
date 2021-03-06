package com.andrdoidlifelang.presentation.ui.setting.theme

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.andrdoidlifelang.domain.model.Theme
import com.andrdoidlifelang.domain.repository.successOr
import com.andrdoidlifelang.domain.usecase.GetAvailableThemesUseCase
import com.andrdoidlifelang.domain.usecase.GetThemeUseCase
import com.andrdoidlifelang.domain.usecase.SetThemeUseCase
import com.andrdoidlifelang.presentation.mapper.map
import com.androidlifelang.corepresentation.model.ThemeUi
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class SettingThemeViewModel @ViewModelInject constructor(
    getAvailableThemesUseCase: GetAvailableThemesUseCase,
    getThemeUseCase: GetThemeUseCase,
    private val setThemeUseCase: SetThemeUseCase
) : ViewModel() {

    val availableThemes: LiveData<List<ThemeUi>> = getAvailableThemesUseCase(Unit).map {
        it.successOr { emptyList() }.map { list -> list.map() }
    }.asLiveData()

    val theme: LiveData<ThemeUi> = getThemeUseCase(Unit).map {
        it.successOr { Theme.SYSTEM }.map()
    }.asLiveData()

    fun setTheme(theme: ThemeUi) {
        viewModelScope.launch {
            setThemeUseCase(theme.map())
        }
    }
}
