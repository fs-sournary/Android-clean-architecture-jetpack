package com.andrdoidlifelang.domain.extension

import com.andrdoidlifelang.domain.model.exception.CleanException
import com.andrdoidlifelang.domain.model.exception.CleanExceptionType

fun Throwable.throwCleanException() {
    when (this) {
        is CleanException -> throw this

        is NullPointerException -> {}

        else -> throw CleanException(CleanExceptionType.UNKNOWN, message)
    }
}
