package com.andrdoidlifelang.domain.usecase

import com.andrdoidlifelang.domain.result.UseCaseResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch

/**
 * Base class for use cases that use the coroutine [Flow]. If there is an exception, we will emit
 * an [UseCaseResult.Error]. Therefore, we don't need handle error in repository classes.
 *
 * @property P the type of parameter.
 * @property R the type of data.
 */
abstract class BaseFlowUseCase<P, R> {

    operator fun invoke(params: P): Flow<UseCaseResult<R>> = execute(params)
        .catch { throwable -> UseCaseResult.Error(Exception(throwable)) }

    protected abstract fun execute(params: P): Flow<UseCaseResult<R>>
}
