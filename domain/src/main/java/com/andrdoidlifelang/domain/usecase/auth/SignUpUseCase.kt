package com.andrdoidlifelang.domain.usecase.auth

import com.andrdoidlifelang.domain.model.exception.CleanException
import com.andrdoidlifelang.domain.model.exception.CleanExceptionType
import com.andrdoidlifelang.domain.repository.AuthRepository
import com.andrdoidlifelang.domain.usecase.UseCase

open class SignUpUseCase(
    private val authRepository: AuthRepository
) : UseCase<SignUpUseCase.Params, Boolean>() {

    data class Params(val username: String, val password: String, val email: String)

    override suspend fun execute(param: Params?): Boolean = param?.let {

        authRepository.signUp(it.username, it.password, it.email)?.let {
            true
        } ?: throw CleanException(CleanExceptionType.UNAUTHORIZED)
    } ?: throw CleanException(CleanExceptionType.PARAM_NULL)
}
