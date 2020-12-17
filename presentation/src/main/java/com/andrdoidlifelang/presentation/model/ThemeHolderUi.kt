package com.andrdoidlifelang.presentation.model

data class ThemeHolderUi(val theme: ThemeUi, val title: String) {
    override fun toString(): String = title
}
