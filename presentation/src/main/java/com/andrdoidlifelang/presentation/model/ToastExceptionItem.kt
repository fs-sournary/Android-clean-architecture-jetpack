package com.andrdoidlifelang.presentation.model

import com.andrdoidlifelang.domain.model.exception.CleanExceptionType

class ToastExceptionItem(
    val type: CleanExceptionType? = null,
    val message: String? = null
) : ExceptionItem
