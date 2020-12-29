package com.andrdoidlifelang.data.ext

import com.andrdoidlifelang.data.model.ErrorResponse
import com.andrdoidlifelang.domain.model.exception.CleanException
import com.andrdoidlifelang.domain.model.exception.CleanExceptionType
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import retrofit2.HttpException
import java.net.HttpURLConnection
import java.net.SocketTimeoutException
import java.net.UnknownHostException

fun Throwable.throwCleanException() {
    when (this) {
        is CleanException -> throw this

        is UnknownHostException -> throw CleanException(CleanExceptionType.NETWORK_NO_CONNECTION, message)

        is SocketTimeoutException -> throw CleanException(CleanExceptionType.NETWORK_TIMEOUT, message)

        is HttpException -> {

            val gson = Gson()
            val typeToken = object : TypeToken<ErrorResponse>() {}.type
            val errorResponse: ErrorResponse? = gson.fromJson(this.response()?.errorBody()?.charStream(), typeToken)
            val errorType = errorResponse?.type
            val errorMsg = errorResponse?.message

            when (code()) {
                // 400
                HttpURLConnection.HTTP_BAD_REQUEST -> {
                }

                // 401
                HttpURLConnection.HTTP_UNAUTHORIZED -> throw CleanException(CleanExceptionType.UNAUTHORIZED, errorMsg)

                // 404
                HttpURLConnection.HTTP_NOT_FOUND -> {
                }

                // 403
                HttpURLConnection.HTTP_FORBIDDEN -> {
                }

                // 500
                HttpURLConnection.HTTP_INTERNAL_ERROR -> {
                }

                // 503
                HttpURLConnection.HTTP_UNAVAILABLE -> {
                    when (errorType) {
                        ErrorResponse.TYPE_APP_FORCE_UPDATE -> throw CleanException(CleanExceptionType.APP_FORCE_UPDATE, errorMsg)

                        ErrorResponse.TYPE_SERVER_MAINTENANCE -> throw CleanException(CleanExceptionType.SERVER_MAINTENANCE, errorMsg)
                    }
                }

                else -> throw CleanException(CleanExceptionType.UNKNOWN, message)
            }
        }
        else -> throw CleanException(CleanExceptionType.UNKNOWN, message)
    }
}
