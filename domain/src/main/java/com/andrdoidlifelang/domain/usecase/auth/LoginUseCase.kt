package com.andrdoidlifelang.domain.usecase.auth

import com.andrdoidlifelang.domain.model.exception.CleanException
import com.andrdoidlifelang.domain.model.exception.CleanExceptionType
import com.andrdoidlifelang.domain.repository.AuthRepository
import com.andrdoidlifelang.domain.usecase.UseCase

open class LoginUseCase(
    private val authRepository: AuthRepository
) : UseCase<LoginUseCase.Params, Boolean>() {

    data class Params(
        val username: String,
        val password: String,
        val rememberLogin: Boolean = false
    )

    override suspend fun execute(param: Params?): Boolean = param?.let {

        authRepository.login(it.username, it.password)?.let { auth ->
            if (param.rememberLogin) {
                authRepository.saveAuth(auth)
            }
            true
        } ?: throw CleanException(CleanExceptionType.UNAUTHORIZED)
    } ?: throw CleanException(CleanExceptionType.PARAM_NULL)
}
