package com.andrdoidlifelang.domain.repository

import com.andrdoidlifelang.domain.model.Theme
import com.andrdoidlifelang.domain.result.UseCaseResult
import kotlinx.coroutines.flow.Flow

interface SettingRepository {

    fun getListTheme(): Flow<UseCaseResult<List<Theme>>>

    fun setTheme(theme: Theme): UseCaseResult<Unit>

    fun getTheme(): Flow<UseCaseResult<Theme>>

    fun getThemeObservable(): Flow<UseCaseResult<Theme>>
}
