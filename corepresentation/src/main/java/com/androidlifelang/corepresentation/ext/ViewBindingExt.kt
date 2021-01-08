package com.androidlifelang.corepresentation.ext

import androidx.databinding.ViewDataBinding

fun <T : ViewDataBinding> T.executeAfter(block: T.() -> Unit) {
    block()
    executePendingBindings()
}
