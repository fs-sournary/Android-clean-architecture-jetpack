package com.andrdoidlifelang.presentation.ui.login

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.map
import com.andrdoidlifelang.domain.model.Theme
import com.andrdoidlifelang.domain.repository.Event
import com.andrdoidlifelang.domain.repository.successOr
import com.andrdoidlifelang.domain.usecase.GetThemeObservableUseCase
import com.andrdoidlifelang.domain.usecase.GetThemeUseCase
import com.andrdoidlifelang.presentation.annotation.DefaultDispatcher
import com.andrdoidlifelang.presentation.mapper.map
import com.andrdoidlifelang.presentation.ui.base.BaseViewModel
import com.androidlifelang.corepresentation.model.ThemeUi
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking

class LoginActivityViewModel @ViewModelInject constructor(
    getThemeObservableUseCase: GetThemeObservableUseCase,
    private val getThemeUseCase: GetThemeUseCase,
    @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher
) : BaseViewModel() {

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
}
