package com.andrdoidlifelang.presentation.model

import com.androidlifelang.corepresentation.model.LanguageUi

data class LanguageHolderUi(val language: LanguageUi, val title: String) {
    override fun toString(): String = title
}
