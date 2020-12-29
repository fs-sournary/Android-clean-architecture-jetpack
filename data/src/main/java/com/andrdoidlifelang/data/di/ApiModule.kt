package com.andrdoidlifelang.data.di

import com.andrdoidlifelang.data.BuildConfig
import com.andrdoidlifelang.data.annotation.APIKeyInterceptorOkHttpClient
import com.andrdoidlifelang.data.annotation.AuthOkHttpClient
import com.andrdoidlifelang.data.annotation.NoInterceptorOkHttpClient
import com.andrdoidlifelang.data.api.AuthApi
import com.andrdoidlifelang.data.api.MovieApi
import com.andrdoidlifelang.data.api.RetrofitBuilder
import com.andrdoidlifelang.data.interceptor.AuthInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object ApiModule {

    @Singleton
    @Provides
    @AuthOkHttpClient
    fun provideAuthRetrofit(retrofitBuilder: RetrofitBuilder): Retrofit = retrofitBuilder
        .setBaseUrl(BuildConfig.BASE_URL_AUTH)
        .build()

    @Singleton
    @Provides
    @NoInterceptorOkHttpClient
    fun provideRetrofit(retrofitBuilder: RetrofitBuilder): Retrofit = retrofitBuilder.build()

    @Singleton
    @Provides
    @APIKeyInterceptorOkHttpClient
    fun provideAPIKeyRetrofit(retrofitBuilder: RetrofitBuilder, authInterceptor: AuthInterceptor): Retrofit = retrofitBuilder
        .addInterceptors(authInterceptor)
        .setBaseUrl(BuildConfig.BASE_URL)
        .build()

    @Singleton
    @Provides
    fun provideMovieApi(@APIKeyInterceptorOkHttpClient retrofit: Retrofit): MovieApi = retrofit.create(MovieApi::class.java)

    @Singleton
    @Provides
    fun provideAuthApi(@AuthOkHttpClient retrofit: Retrofit): AuthApi = retrofit.create(AuthApi::class.java)
}
