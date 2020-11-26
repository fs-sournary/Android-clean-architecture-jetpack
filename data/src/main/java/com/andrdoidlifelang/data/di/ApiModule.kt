package com.andrdoidlifelang.data.di

import com.andrdoidlifelang.data.api.ApiManager
import com.andrdoidlifelang.data.api.MovieApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object ApiModule {

    @Singleton
    @Provides
    fun provideMovieApi(): MovieApi = ApiManager.getMovieApi()
}
