package com.andrdoidlifelang.data.di

import android.content.Context
import androidx.room.Room
import com.andrdoidlifelang.data.db.MovieDatabase
import com.andrdoidlifelang.data.db.VideoDao
import com.andrdoidlifelang.data.pref.AppPreference
import com.andrdoidlifelang.data.pref.AppPreferenceImpl
import com.andrdoidlifelang.data.util.Constant
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object AppModule {

    @Singleton
    @Provides
    fun provideAppPreference(@ApplicationContext context: Context): AppPreference =
        AppPreferenceImpl(context)

    @Singleton
    @Provides
    fun provideVideoDao(db: MovieDatabase): VideoDao = db.getVideoDao()

    @Singleton
    @Provides
    fun provideMovieDatabase(@ApplicationContext context: Context): MovieDatabase =
        Room.databaseBuilder(context, MovieDatabase::class.java, Constant.MOVIE_DB_NAME).build()
}
