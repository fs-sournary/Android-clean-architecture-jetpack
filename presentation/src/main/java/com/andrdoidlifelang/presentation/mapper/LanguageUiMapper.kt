package com.andrdoidlifelang.presentation.mapper

import com.andrdoidlifelang.domain.model.Language
import com.androidlifelang.corepresentation.model.LanguageUi

fun Language.map(): LanguageUi = when (this) {
    Language.EN -> LanguageUi.EN
    Language.VI -> LanguageUi.VI
}

fun LanguageUi.map(): Language = when (this) {
    LanguageUi.EN -> Language.EN
    LanguageUi.VI -> Language.VI
}
