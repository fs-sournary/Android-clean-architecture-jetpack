package com.andrdoidlifelang.domain.repository

/**
 * A generic class handles data with its loading status: loading, success and error.
 */
sealed class UseCaseResult<out R> {

    data class Success<out T>(val data: T) : UseCaseResult<T>()

    data class Error(val exception: Exception) : UseCaseResult<Nothing>()

    object Loading : UseCaseResult<Nothing>()

    override fun toString(): String = when (this) {
        is Success -> "Success[data=$data]"
        is Error -> "Error[error=$exception]"
        is Loading -> "Loading"
    }
}

/**
 * A property returns data if result is [UseCaseResult.Success]. Otherwise, it will return null.
 */
val <T> UseCaseResult<T>.data: T?
    get() = (this as? UseCaseResult.Success)?.data

/**
 * A property returns true if result is [UseCaseResult.Success] and its data isn't null.
 */
val UseCaseResult<*>.succeeded
    get() = this is UseCaseResult.Success && data != null

/**
 * If result is [UseCaseResult.Success], this function will return its data. Otherwise, it will
 * return the [fallback]'s result.
 */
fun <T> UseCaseResult<T>.successOr(fallback: () -> T): T =
    (this as? UseCaseResult.Success)?.data ?: fallback()
