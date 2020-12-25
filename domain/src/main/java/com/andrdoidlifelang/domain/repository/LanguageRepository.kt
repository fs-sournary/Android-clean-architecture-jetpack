package com.andrdoidlifelang.domain.repository

import com.andrdoidlifelang.domain.model.Language
import kotlinx.coroutines.flow.Flow

interface LanguageRepository {

    fun getListLanguage(): Flow<UseCaseResult<List<Language>>>

    fun setLanguage(language: Language): UseCaseResult<Unit>

    fun getLanguage(): Flow<UseCaseResult<Language>>

    fun getLanguageObservable(): Flow<UseCaseResult<Language>>
}
