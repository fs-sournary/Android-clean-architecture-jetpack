package com.andrdoidlifelang.presentation.di

import com.andrdoidlifelang.domain.repository.HomeRepository
import com.andrdoidlifelang.domain.repository.SettingRepository
import com.andrdoidlifelang.domain.usecase.GetAvailableThemesUseCase
import com.andrdoidlifelang.domain.usecase.GetMovieVideoUseCase
import com.andrdoidlifelang.domain.usecase.GetNowPlayingMovieUseCase
import com.andrdoidlifelang.domain.usecase.GetPopularMovieUseCase
import com.andrdoidlifelang.domain.usecase.GetThemeObservableUseCase
import com.andrdoidlifelang.domain.usecase.GetThemeUseCase
import com.andrdoidlifelang.domain.usecase.GetTopRateMovieUseCase
import com.andrdoidlifelang.domain.usecase.GetUpcomingMovieUseCase
import com.andrdoidlifelang.domain.usecase.SetThemeUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object UseCaseModule {

    @Singleton
    @Provides
    fun provideGetNowPlayingMovieUseCase(
        homeRepository: HomeRepository
    ): GetNowPlayingMovieUseCase = GetNowPlayingMovieUseCase(homeRepository)

    @Singleton
    @Provides
    fun provideGetUpcomingMovieUseCase(
        homeRepository: HomeRepository
    ): GetUpcomingMovieUseCase = GetUpcomingMovieUseCase(homeRepository)

    @Singleton
    @Provides
    fun provideGetTopRateMovieUseCase(
        homeRepository: HomeRepository
    ): GetTopRateMovieUseCase = GetTopRateMovieUseCase(homeRepository)

    @Singleton
    @Provides
    fun provideGetPopularMovieUseCase(
        homeRepository: HomeRepository
    ): GetPopularMovieUseCase = GetPopularMovieUseCase(homeRepository)

    @Singleton
    @Provides
    fun provideGetMovieVideoUseCase(
        homeRepository: HomeRepository
    ): GetMovieVideoUseCase = GetMovieVideoUseCase(homeRepository)

    @Singleton
    @Provides
    fun provideGetAvailableThemesUseCase(
        settingRepository: SettingRepository
    ): GetAvailableThemesUseCase = GetAvailableThemesUseCase(settingRepository)

    @Singleton
    @Provides
    fun provideGetThemeUseCase(
        settingRepository: SettingRepository
    ): GetThemeUseCase = GetThemeUseCase(settingRepository)

    @Singleton
    @Provides
    fun provideSetThemeUseCase(
        settingRepository: SettingRepository
    ): SetThemeUseCase = SetThemeUseCase(settingRepository)

    @Singleton
    @Provides
    fun provideGetThemeObservableUseCase(
        settingRepository: SettingRepository
    ): GetThemeObservableUseCase = GetThemeObservableUseCase(settingRepository)
}
