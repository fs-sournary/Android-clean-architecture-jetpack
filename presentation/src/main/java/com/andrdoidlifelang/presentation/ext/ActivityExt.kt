package com.andrdoidlifelang.presentation.ext

import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.andrdoidlifelang.presentation.model.ThemeUi

/**
 * Update theme base on user setting.
 */
fun AppCompatActivity.updateTheme(theme: ThemeUi) {
    delegate.localNightMode = when (theme) {
        ThemeUi.LIGHT -> AppCompatDelegate.MODE_NIGHT_NO
        ThemeUi.DARK -> AppCompatDelegate.MODE_NIGHT_YES
        ThemeUi.SYSTEM -> AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
        else -> AppCompatDelegate.MODE_NIGHT_AUTO_BATTERY
    }
}
