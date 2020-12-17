package com.andrdoidlifelang.domain.usecase

import com.andrdoidlifelang.domain.result.UseCaseResult

/**
 * Base class for use cases that use the suspend function of coroutines. If there is an exception,
 * we will return a [UseCaseResult.Error]. Therefore, we don't need to handle exception in repositories.
 *
 * @property P the type of parameter.
 * @property R the type of data.
 */
abstract class BaseCoroutineUseCase<P, R> {

    suspend operator fun invoke(params: P): UseCaseResult<R> = try {
        val result = execute(params)
        UseCaseResult.Success(result)
    } catch (e: Exception) {
        UseCaseResult.Error(e)
    }

    protected abstract suspend fun execute(params: P): R
}
