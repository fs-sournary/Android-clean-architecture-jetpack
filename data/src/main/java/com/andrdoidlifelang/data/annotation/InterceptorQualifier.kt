package com.andrdoidlifelang.data.annotation

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class APIKeyInterceptorOkHttpClient

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class NoInterceptorOkHttpClient

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class AuthOkHttpClient
