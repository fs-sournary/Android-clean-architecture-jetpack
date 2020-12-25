package com.andrdoidlifelang.presentation.mapper

import android.os.Build
import com.andrdoidlifelang.domain.model.Theme
import com.andrdoidlifelang.domain.repository.UseCaseResult
import com.andrdoidlifelang.domain.repository.data
import com.androidlifelang.corepresentation.model.ThemeUi

fun UseCaseResult<List<Theme>>.mapList(): UseCaseResult<List<ThemeUi>> {
    val themeUis = this.data?.map { it.map() } ?: emptyList()
    return UseCaseResult.Success(themeUis)
}

fun UseCaseResult<Theme>.map(): UseCaseResult<ThemeUi> {
    val themeUi = this.data?.map()
    return UseCaseResult.Success(
        themeUi ?: if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            ThemeUi.SYSTEM
        } else {
            ThemeUi.BATTERY_SAVER
        }
    )
}

fun Theme.map(): ThemeUi = when (this) {
    Theme.SYSTEM -> ThemeUi.SYSTEM
    Theme.BATTERY_SAVER -> ThemeUi.BATTERY_SAVER
    Theme.LIGHT -> ThemeUi.LIGHT
    Theme.DARK -> ThemeUi.DARK
}

fun ThemeUi.map(): Theme = when (this) {
    ThemeUi.SYSTEM -> Theme.SYSTEM
    ThemeUi.BATTERY_SAVER -> Theme.BATTERY_SAVER
    ThemeUi.LIGHT -> Theme.LIGHT
    ThemeUi.DARK -> Theme.DARK
}
