package com.andrdoidlifelang.data.di

import com.andrdoidlifelang.data.net.MovieApi
import com.andrdoidlifelang.data.pref.AppPreference
import com.andrdoidlifelang.data.repository.SignInRepositoryImpl
import com.andrdoidlifelang.data.repository.HomeRepositoryImpl
import com.andrdoidlifelang.data.repository.SettingRepositoryImpl
import com.andrdoidlifelang.domain.repository.SignInRepository
import com.andrdoidlifelang.domain.repository.HomeRepository
import com.andrdoidlifelang.domain.repository.SettingRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object RepositoryModule {

    @Singleton
    @Provides
    fun provideThemeRepository(appPreference: AppPreference): SettingRepository =
        SettingRepositoryImpl(appPreference)

    @Singleton
    @Provides
    fun provideAuthStateUserRepository(): SignInRepository = SignInRepositoryImpl()

    @Singleton
    @Provides
    fun provideMovieRepository(movieApi: MovieApi): HomeRepository = HomeRepositoryImpl(movieApi)
}
