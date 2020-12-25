package com.andrdoidlifelang.presentation.di

import com.andrdoidlifelang.domain.repository.MovieRepository
import com.andrdoidlifelang.domain.repository.ThemeRepository
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
        movieRepository: MovieRepository
    ): GetNowPlayingMovieUseCase = GetNowPlayingMovieUseCase(movieRepository)

    @Singleton
    @Provides
    fun provideGetUpcomingMovieUseCase(
        movieRepository: MovieRepository
    ): GetUpcomingMovieUseCase = GetUpcomingMovieUseCase(movieRepository)

    @Singleton
    @Provides
    fun provideGetTopRateMovieUseCase(
        movieRepository: MovieRepository
    ): GetTopRateMovieUseCase = GetTopRateMovieUseCase(movieRepository)

    @Singleton
    @Provides
    fun provideGetPopularMovieUseCase(
        movieRepository: MovieRepository
    ): GetPopularMovieUseCase = GetPopularMovieUseCase(movieRepository)

    @Singleton
    @Provides
    fun provideGetMovieVideoUseCase(
        movieRepository: MovieRepository
    ): GetMovieVideoUseCase = GetMovieVideoUseCase(movieRepository)

    @Singleton
    @Provides
    fun provideGetAvailableThemesUseCase(
        themeRepository: ThemeRepository
    ): GetAvailableThemesUseCase = GetAvailableThemesUseCase(themeRepository)

    @Singleton
    @Provides
    fun provideGetThemeUseCase(
        themeRepository: ThemeRepository
    ): GetThemeUseCase = GetThemeUseCase(themeRepository)

    @Singleton
    @Provides
    fun provideSetThemeUseCase(
        themeRepository: ThemeRepository
    ): SetThemeUseCase = SetThemeUseCase(themeRepository)

    @Singleton
    @Provides
    fun provideGetThemeObservableUseCase(
        themeRepository: ThemeRepository
    ): GetThemeObservableUseCase = GetThemeObservableUseCase(themeRepository)
}
