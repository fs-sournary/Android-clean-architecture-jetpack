package com.andrdoidlifelang.data.di

import android.content.Context
import androidx.room.Room
import com.andrdoidlifelang.data.db.MovieDatabase
import com.andrdoidlifelang.data.db.VideoDao
import com.andrdoidlifelang.data.pref.AppPreference
import com.andrdoidlifelang.data.pref.AppPreferenceImpl
import com.andrdoidlifelang.data.util.Constant
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object AppModule {

    @FlowPreview
    @ExperimentalCoroutinesApi
    @Provides
    @Singleton
    fun provideAppPreference(appPreferenceImpl: AppPreferenceImpl): AppPreference {
        return appPreferenceImpl
    }

    @FlowPreview
    @ExperimentalCoroutinesApi
    @Provides
    @Singleton
    fun provideAppPreferenceImpl(@ApplicationContext context: Context, gson: Gson): AppPreferenceImpl {
        return AppPreferenceImpl(context, gson)
    }

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return Gson()
    }

    @Singleton
    @Provides
    fun provideVideoDao(db: MovieDatabase): VideoDao = db.getVideoDao()

    @Singleton
    @Provides
    fun provideMovieDatabase(@ApplicationContext context: Context): MovieDatabase =
        Room.databaseBuilder(context, MovieDatabase::class.java, Constant.MOVIE_DB_NAME).build()
}
