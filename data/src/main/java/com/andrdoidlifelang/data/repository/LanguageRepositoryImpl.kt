package com.andrdoidlifelang.data.repository

import com.andrdoidlifelang.data.pref.AppPreference
import com.andrdoidlifelang.domain.model.Language
import com.andrdoidlifelang.domain.model.Language.EN
import com.andrdoidlifelang.domain.model.Language.VI
import com.andrdoidlifelang.domain.model.languageFromStorageKey
import com.andrdoidlifelang.domain.repository.LanguageRepository
import com.andrdoidlifelang.domain.repository.UseCaseResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LanguageRepositoryImpl @Inject constructor(private val appPreference: AppPreference) : LanguageRepository {

    override fun getListLanguage(): Flow<UseCaseResult<List<Language>>> = flow {
        emit(UseCaseResult.Success(listOf(EN, VI)))
    }

    override fun setLanguage(language: Language): UseCaseResult<Unit> =
        UseCaseResult.Success(appPreference.setLanguage(language.storageKey))

    override fun getLanguage(): Flow<UseCaseResult<Language>> = flow {
        val language = appPreference.getLanguage()
        emit(
            languageFromStorageKey(language)?.let {
                UseCaseResult.Success(it)
            } ?: run {
                UseCaseResult.Error(Exception("Cannot find language"))
            }
        )
    }

    override fun getLanguageObservable(): Flow<UseCaseResult<Language>> = appPreference.observableSelectLanguage().map {
        UseCaseResult.Success(languageFromStorageKey(it) ?: EN)
    }
}
