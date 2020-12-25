package com.andrdoidlifelang.presentation.ui.setting.language

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.andrdoidlifelang.domain.model.Language.EN
import com.andrdoidlifelang.domain.repository.successOr
import com.andrdoidlifelang.domain.usecase.GetAvailableLanguageUseCase
import com.andrdoidlifelang.domain.usecase.GetLanguageUseCase
import com.andrdoidlifelang.domain.usecase.SetLanguageUseCase
import com.andrdoidlifelang.presentation.mapper.map
import com.androidlifelang.corepresentation.model.LanguageUi
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class SettingLanguageViewModel @ViewModelInject constructor(
    getAvailableLanguageUseCase: GetAvailableLanguageUseCase,
    getLanguageUseCase: GetLanguageUseCase,
    private val setLanguageUseCase: SetLanguageUseCase
) : ViewModel() {

    val availableLanguage: LiveData<List<LanguageUi>> = getAvailableLanguageUseCase(Unit)
        .map { it.successOr { emptyList() }.map { list -> list.map() } }
        .asLiveData()

    val currentLanguage: LiveData<LanguageUi> = getLanguageUseCase(Unit)
        .map { it.successOr { EN }.map() }
        .asLiveData()

    private val _clickItem = MutableLiveData<Unit>()
    val clickItem: LiveData<Unit> = _clickItem

    fun onItemLanguageClick(item: LanguageUi) {
        _clickItem.value = Unit
        viewModelScope.launch {
            setLanguageUseCase.invoke(item.map())
        }
    }
}
