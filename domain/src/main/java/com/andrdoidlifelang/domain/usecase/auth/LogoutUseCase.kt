package com.andrdoidlifelang.domain.usecase.auth

import com.andrdoidlifelang.domain.model.exception.CleanException
import com.andrdoidlifelang.domain.model.exception.CleanExceptionType
import com.andrdoidlifelang.domain.repository.AuthRepository
import com.andrdoidlifelang.domain.usecase.UseCase

open class LogoutUseCase(
    private val authRepository: AuthRepository
) : UseCase<Unit, Unit>() {
    override suspend fun execute(param: Unit?) {

        if (authRepository.logout()) {
            authRepository.clearAuth()
        } else {
            throw CleanException(CleanExceptionType.UNKNOWN)
        }
    }
}
