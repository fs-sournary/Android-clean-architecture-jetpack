package com.andrdoidlifelang.presentation.model

import com.andrdoidlifelang.domain.model.exception.CleanExceptionType

class AlertExceptionItem(
    val type: CleanExceptionType? = null,
    val message: String? = null,
    val title: String? = null,
    val positiveButton: String? = null,
    val negativeButton: String? = null
) : ExceptionItem
