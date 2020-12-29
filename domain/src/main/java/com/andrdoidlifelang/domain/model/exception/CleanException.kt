package com.andrdoidlifelang.domain.model.exception

open class CleanException(
    open val type: CleanExceptionType? = null,
    override val message: String? = null
) : Throwable(message)
