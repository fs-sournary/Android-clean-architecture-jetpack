package com.andrdoidlifelang.presentation.di

import com.andrdoidlifelang.presentation.util.analytics.AnalyticsHelper
import com.andrdoidlifelang.presentation.util.analytics.FirebaseAnalyticsHelper
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
