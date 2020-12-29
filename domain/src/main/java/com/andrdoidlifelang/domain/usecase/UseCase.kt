package com.andrdoidlifelang.domain.usecase

abstract class UseCase<in Param, out T> where T : Any {

    abstract suspend fun execute(param: Param? = null): T

    open class Param
}
