package com.andrdoidlifelang.data.repository

import android.os.Build
import com.andrdoidlifelang.data.pref.AppPreference
import com.andrdoidlifelang.domain.model.Theme
import com.andrdoidlifelang.domain.model.themeFromStorageKey
import com.andrdoidlifelang.domain.repository.ThemeRepository
import com.andrdoidlifelang.domain.repository.UseCaseResult
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

@Singleton
class ThemeRepositoryImpl @Inject constructor(private val appPreference: AppPreference) : ThemeRepository {

    override fun getListTheme(): Flow<UseCaseResult<List<Theme>>> = flow {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            emit(UseCaseResult.Success(listOf(Theme.LIGHT, Theme.DARK, Theme.SYSTEM)))
        } else {
            emit(UseCaseResult.Success(listOf(Theme.LIGHT, Theme.DARK, Theme.BATTERY_SAVER)))
        }
    }

    override fun setTheme(theme: Theme): UseCaseResult<Unit> =
        UseCaseResult.Success(appPreference.setTheme(theme.storageKey))

    override fun getTheme(): Flow<UseCaseResult<Theme>> = flow {
        val selectedTheme = appPreference.getTheme()
        emit(
            UseCaseResult.Success(
                themeFromStorageKey(selectedTheme) ?: if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    Theme.SYSTEM
                } else {
                    Theme.BATTERY_SAVER
                }
            )
        )
    }

    override fun getThemeObservable(): Flow<UseCaseResult<Theme>> = appPreference.observableSelectedTheme().map {
        val theme = themeFromStorageKey(it) ?: when {
            Build.VERSION.SDK_INT >= 29 -> Theme.SYSTEM
            else -> Theme.BATTERY_SAVER
        }
        UseCaseResult.Success(theme)
    }
}
