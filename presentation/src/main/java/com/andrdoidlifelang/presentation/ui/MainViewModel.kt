package com.andrdoidlifelang.presentation.ui

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.map
import com.andrdoidlifelang.domain.model.Language.EN
import com.andrdoidlifelang.domain.model.Theme
import com.andrdoidlifelang.domain.repository.Event
import com.andrdoidlifelang.domain.repository.successOr
import com.andrdoidlifelang.domain.usecase.GetLanguageObservableUseCase
import com.andrdoidlifelang.domain.usecase.GetThemeObservableUseCase
import com.andrdoidlifelang.domain.usecase.GetThemeUseCase
import com.andrdoidlifelang.presentation.mapper.map
import com.androidlifelang.corepresentation.model.LanguageUi
import com.androidlifelang.corepresentation.model.ThemeUi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking

class MainViewModel @ViewModelInject constructor(
    @Assisted savedStateHandle: SavedStateHandle,
    getThemeObservableUseCase: GetThemeObservableUseCase,
    private val getThemeUseCase: GetThemeUseCase,
    getLanguageObservableUseCase: GetLanguageObservableUseCase
) : ViewModel() {

    val theme: LiveData<Event<ThemeUi>> = getThemeObservableUseCase(Unit)
        .map { it.successOr { Theme.SYSTEM }.map() }
        .asLiveData()
        .map { Event(it) }

    val currentTheme: ThemeUi
        get() = runBlocking {
            var theme: ThemeUi = ThemeUi.SYSTEM
            getThemeUseCase(Unit).collect {
                theme = it.successOr { Theme.LIGHT }.map()
            }
            theme
        }

    val language: LiveData<Event<LanguageUi>> = getLanguageObservableUseCase(Unit)
        .map { it.successOr { EN }.map() }
        .asLiveData()
        .map { Event(it) }
}
