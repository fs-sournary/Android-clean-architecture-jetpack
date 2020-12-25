package com.andrdoidlifelang.domain.repository

import com.andrdoidlifelang.domain.model.Theme
import kotlinx.coroutines.flow.Flow

interface ThemeRepository {

    fun getListTheme(): Flow<UseCaseResult<List<Theme>>>

    fun setTheme(theme: Theme): UseCaseResult<Unit>

    fun getTheme(): Flow<UseCaseResult<Theme>>

    fun getThemeObservable(): Flow<UseCaseResult<Theme>>
}
