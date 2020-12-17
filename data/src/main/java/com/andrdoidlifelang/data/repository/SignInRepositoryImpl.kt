package com.andrdoidlifelang.data.repository

import com.andrdoidlifelang.domain.model.AuthBasicUserInfo
import com.andrdoidlifelang.domain.repository.SignInRepository
import com.andrdoidlifelang.domain.result.UseCaseResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SignInRepositoryImpl : SignInRepository{

    override fun getBasicUserInfo(): Flow<UseCaseResult<AuthBasicUserInfo>> = flow {  }
}
