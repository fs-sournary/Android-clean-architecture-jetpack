package com.andrdoidlifelang.presentation.model

import com.androidlifelang.corepresentation.model.ThemeUi

data class ThemeHolderUi(val theme: ThemeUi, val title: String) {
    override fun toString(): String = title
}
