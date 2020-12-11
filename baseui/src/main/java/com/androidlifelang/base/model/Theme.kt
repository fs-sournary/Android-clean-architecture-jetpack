package com.androidlifelang.base.model

enum class Theme(val storageKey: String) {
    LIGHT("light"),
    DARK("dark"),
    SYSTEM("system"),
    BATTERY_SAVER("battery_saver")
}

/**
 * Return the matching [Theme] for the given [storageKey] value.
 */
fun themeForStorageKey(storageKey: String): Theme? =
    Theme.values().firstOrNull { it.storageKey == storageKey }
