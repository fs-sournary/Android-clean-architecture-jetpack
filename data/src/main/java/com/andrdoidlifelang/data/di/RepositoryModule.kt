package com.andrdoidlifelang.data.di

import com.andrdoidlifelang.data.repository.MovieRepositoryImpl
import com.andrdoidlifelang.data.repository.ThemeRepositoryImpl
import com.andrdoidlifelang.domain.repository.MovieRepository
import com.andrdoidlifelang.domain.repository.ThemeRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@InstallIn(ApplicationComponent::class)
@Module
abstract class RepositoryModule {

    @Binds
    abstract fun bindMovieRepository(impl: MovieRepositoryImpl): MovieRepository

    @Binds
    abstract fun bindThemeRepository(impl: ThemeRepositoryImpl): ThemeRepository
}
