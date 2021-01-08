package com.andrdoidlifelang.presentation.ui.setting.detailsetting

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.map
import com.andrdoidlifelang.domain.model.Language.EN
import com.andrdoidlifelang.domain.model.Theme
import com.andrdoidlifelang.domain.repository.Event
import com.andrdoidlifelang.domain.repository.successOr
import com.andrdoidlifelang.domain.usecase.GetLanguageObservableUseCase
import com.andrdoidlifelang.domain.usecase.GetLanguageUseCase
import com.andrdoidlifelang.domain.usecase.GetThemeObservableUseCase
import com.andrdoidlifelang.domain.usecase.GetThemeUseCase
import com.andrdoidlifelang.presentation.mapper.map
import com.androidlifelang.corepresentation.model.LanguageUi
import com.androidlifelang.corepresentation.model.ThemeUi
import kotlinx.coroutines.flow.map

class DetailSettingViewModel @ViewModelInject constructor(
    getThemeObservableUseCase: GetThemeObservableUseCase,
    getThemeUseCase: GetThemeUseCase,
    getLanguageUseCase: GetLanguageUseCase,
    getLanguageObservableUseCase: GetLanguageObservableUseCase
) : ViewModel() {

    val themeObservable: LiveData<ThemeUi> = getThemeObservableUseCase(Unit)
        .map { it.successOr { Theme.SYSTEM }.map() }
        .asLiveData()

    val currentTheme: LiveData<Event<ThemeUi>> = getThemeUseCase(Unit)
        .map { it.successOr { Theme.SYSTEM }.map() }
        .asLiveData()
        .map { Event(it) }

    val languageObservable: LiveData<LanguageUi> = getLanguageObservableUseCase(Unit)
        .map { it.successOr { EN }.map() }
        .asLiveData()

    val currentLanguage: LiveData<Event<LanguageUi>> = getLanguageUseCase(Unit)
        .map { it.successOr { EN }.map() }
        .asLiveData()
        .map { Event(it) }
}
