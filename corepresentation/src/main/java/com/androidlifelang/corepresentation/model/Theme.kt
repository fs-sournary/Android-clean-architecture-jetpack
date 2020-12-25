package com.androidlifelang.corepresentation.model

enum class ThemeUi(val storageKey: String) {
    LIGHT("light"),
    DARK("dark"),
    SYSTEM("system"),
    BATTERY_SAVER("battery_saver")
}

/**
 * Return the matching [ThemeUi] for the given [storageKey] value.
 */
fun themeForStorageKey(storageKey: String): ThemeUi? =
    ThemeUi.values().firstOrNull { it.storageKey == storageKey }
