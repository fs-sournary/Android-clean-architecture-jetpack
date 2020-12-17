package com.andrdoidlifelang.domain.usecase

import com.andrdoidlifelang.domain.model.AuthBasicUserInfo
import com.andrdoidlifelang.domain.repository.SignInRepository
import com.andrdoidlifelang.domain.result.UseCaseResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collect

class UserAuthUseCase(
    private val signInStateUserRepository: SignInRepository
) : BaseFlowUseCase<Unit, AuthBasicUserInfo>() {

    override fun execute(params: Unit): Flow<UseCaseResult<AuthBasicUserInfo>> {
        return channelFlow {
            signInStateUserRepository.getBasicUserInfo().collect {
                if (it is UseCaseResult.Success) {

                } else {
                    channel.offer(UseCaseResult.Error(Exception("Firebase error")))
                }
            }
        }
    }
}
