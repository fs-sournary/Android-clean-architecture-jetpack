package com.andrdoidlifelang.presentation.ui

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.asLiveData
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.andrdoidlifelang.domain.model.Theme
import com.andrdoidlifelang.domain.repository.Event
import com.andrdoidlifelang.domain.repository.successOr
import com.andrdoidlifelang.domain.usecase.GetThemeObservableUseCase
import com.andrdoidlifelang.domain.usecase.GetThemeUseCase
import com.andrdoidlifelang.domain.usecase.auth.LogoutUseCase
import com.andrdoidlifelang.presentation.annotation.DefaultDispatcher
import com.andrdoidlifelang.presentation.extention.mapToCleanException
import com.andrdoidlifelang.presentation.mapper.map
import com.andrdoidlifelang.presentation.ui.base.BaseViewModel
import com.androidlifelang.corepresentation.model.ThemeUi
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MainViewModel @ViewModelInject constructor(
    @Assisted savedStateHandle: SavedStateHandle,
    getThemeObservableUseCase: GetThemeObservableUseCase,
    private val getThemeUseCase: GetThemeUseCase,
    private val logoutUseCase: LogoutUseCase,
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

    private val _logoutEvent = MutableLiveData<Event<Unit>>()
    val logoutEvent: LiveData<Event<Unit>>
        get() = _logoutEvent

    fun logout() {
        viewModelScope.launch(defaultDispatcher) {
            runCatching {
                logoutUseCase.execute()
                _logoutEvent.postValue(Event(Unit))
            }.getOrElse {
                it.mapToCleanException().apply {
                    setExceptionAsync(this)
                }
            }
        }
    }
}
