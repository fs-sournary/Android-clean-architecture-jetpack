package com.andrdoidlifelang.domain.model

enum class Language(val storageKey: String) {
    EN("en"),
    VI("vi")
}

fun languageFromStorageKey(storageKey: String): Language? =
    Language.values().firstOrNull { it.storageKey == storageKey }
