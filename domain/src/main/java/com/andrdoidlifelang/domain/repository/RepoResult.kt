package com.andrdoidlifelang.domain.repository

sealed class RepoResult<out R> {

    data class Success<out T>(val data: T) : RepoResult<T>()

    data class Error(val exception: Exception) : RepoResult<Nothing>()

    object Loading : RepoResult<Nothing>()

    override fun toString(): String = when (this) {
        is Success -> "Success[data=$data]"
        is Error -> "Error[error=$exception]"
        is Loading -> "Loading"
    }
}
