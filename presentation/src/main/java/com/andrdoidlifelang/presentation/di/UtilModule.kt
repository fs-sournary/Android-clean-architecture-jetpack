package com.andrdoidlifelang.presentation.di

import com.andrdoidlifelang.presentation.analytics.AnalyticsHelper
import com.andrdoidlifelang.presentation.analytics.FirebaseAnalyticsHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object UtilModule {

    @Singleton
    @Provides
    fun provideAnalyticsHelper(): AnalyticsHelper = FirebaseAnalyticsHelper()
}
