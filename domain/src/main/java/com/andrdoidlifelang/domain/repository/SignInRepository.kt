package com.andrdoidlifelang.domain.repository

import com.andrdoidlifelang.domain.model.AuthBasicUserInfo
import com.andrdoidlifelang.domain.result.UseCaseResult
import kotlinx.coroutines.flow.Flow

interface SignInRepository {

    fun getBasicUserInfo(): Flow<UseCaseResult<AuthBasicUserInfo>>
}
