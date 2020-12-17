package com.andrdoidlifelang.data.auth

import com.andrdoidlifelang.domain.model.AuthBasicUserInfo
import com.andrdoidlifelang.domain.result.UseCaseResult
import kotlinx.coroutines.flow.Flow

interface AuthStateUserDataSource {

    fun getBasicUserInfo(): Flow<UseCaseResult<AuthBasicUserInfo>>
}
