package com.andrdoidlifelang.domain.usecase.auth

import com.andrdoidlifelang.domain.repository.AuthRepository
import com.andrdoidlifelang.domain.usecase.UseCase

open class CheckLoginUseCase(
    private val authRepository: AuthRepository
) : UseCase<Unit, Boolean>() {

    override suspend fun execute(param: Unit?): Boolean {
        return authRepository.isLogin()
    }
}
