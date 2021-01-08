package com.androidlifelang.corepresentation.model

import com.androidlifelang.corepresentation.model.LanguageUi.EN
import com.androidlifelang.corepresentation.model.LanguageUi.VI
import java.util.Locale

enum class LanguageUi(val storageKey: String) {
    EN("English"),
    VI("Tiếng Việt")
}

fun LanguageUi.getLocale(): Locale = when (this) {
    EN -> Locale("en")
    VI -> Locale("vi")
}
