package com.andrdoidlifelang.presentation.extention

import android.content.Context
import com.andrdoidlifelang.domain.model.exception.CleanException
import com.andrdoidlifelang.domain.model.exception.CleanExceptionType
import com.andrdoidlifelang.presentation.R
import com.andrdoidlifelang.presentation.model.AlertExceptionItem
import com.andrdoidlifelang.presentation.model.DialogExceptionItem
import com.andrdoidlifelang.presentation.model.ExceptionItem
import com.andrdoidlifelang.presentation.model.SnackBarExceptionItem

fun Throwable.throwCleanException() {
    when (this) {
        is CleanException -> throw this

        is NullPointerException -> {}

        else -> throw CleanException(CleanExceptionType.UNKNOWN, message)
    }
}

fun Throwable.mapToCleanException(): CleanException = when (this) {
    is CleanException -> this

    else -> CleanException(CleanExceptionType.UNKNOWN, message)
}

fun CleanException.mapToExceptionItem(context: Context): ExceptionItem = when (type) {
    CleanExceptionType.DATA_NULL_OR_EMPTY -> AlertExceptionItem(
        title = context.getString(R.string.notification),
        message = context.getString(R.string.data_empty),
        positiveButton = context.getString(android.R.string.ok)
    )

    CleanExceptionType.NETWORK_NO_CONNECTION -> SnackBarExceptionItem(
        message = context.getString(R.string.no_internet)
    )

    CleanExceptionType.NETWORK_TIMEOUT -> SnackBarExceptionItem(
        message = context.getString(R.string.connection_timeout)
    )

    CleanExceptionType.SERVER_MAINTENANCE -> DialogExceptionItem(
        title = context.getString(R.string.server_maintenance),
        message = context.getString(R.string.server_maintenance_message),
        positiveButton = context.getString(R.string.try_later),
        type = type
    )

    CleanExceptionType.APP_FORCE_UPDATE -> DialogExceptionItem(
        title = context.getString(R.string.new_version_app),
        message = context.getString(R.string.new_version_app_message),
        positiveButton = context.getString(R.string.update),
        negativeButton = context.getString(R.string.no_thank),
        type = type
    )

    CleanExceptionType.UNAUTHORIZED -> AlertExceptionItem(
        title = context.getString(R.string.title_error_login),
        message = message?.let { it } ?: context.getString(R.string.login_fail),
        positiveButton = context.getString(android.R.string.ok)
    )

    else -> AlertExceptionItem(
        title = "",
        message = message?.let { it } ?: "Oops, something went wrong."
    )
}
