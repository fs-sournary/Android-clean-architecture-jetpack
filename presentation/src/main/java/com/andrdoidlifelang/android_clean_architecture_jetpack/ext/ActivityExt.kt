package com.andrdoidlifelang.android_clean_architecture_jetpack.ext

import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.andrdoidlifelang.android_clean_architecture_jetpack.model.Theme

/**
 * Update theme base on user setting.
 */
fun AppCompatActivity.updateTheme(theme: Theme) {
    delegate.localNightMode = when (theme) {
        Theme.LIGHT -> AppCompatDelegate.MODE_NIGHT_NO
        Theme.DARK -> AppCompatDelegate.MODE_NIGHT_YES
        Theme.SYSTEM -> AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
        else -> AppCompatDelegate.MODE_NIGHT_AUTO_BATTERY
    }
}
