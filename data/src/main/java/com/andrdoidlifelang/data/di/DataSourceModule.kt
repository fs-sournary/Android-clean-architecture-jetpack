package com.andrdoidlifelang.data.di

import android.content.Context
import androidx.room.Room
import com.andrdoidlifelang.data.auth.AuthStateUserDataSource
import com.andrdoidlifelang.data.auth.FirebaseAuthStateUserDataSource
import com.andrdoidlifelang.data.db.MovieDatabase
import com.andrdoidlifelang.data.db.VideoDao
import com.andrdoidlifelang.data.fcm.FcmTokenUpdater
import com.andrdoidlifelang.data.fcm.FcmTopicSubscriber
import com.andrdoidlifelang.data.fcm.TopicSubscriber
import com.andrdoidlifelang.data.net.ApiManager
import com.andrdoidlifelang.data.net.MovieApi
import com.andrdoidlifelang.data.pref.AppPreference
import com.andrdoidlifelang.data.pref.AppPreferenceImpl
import com.andrdoidlifelang.data.userevent.FirestoreUserEventDataSource
import com.andrdoidlifelang.data.userevent.UserEventDataSource
import com.andrdoidlifelang.data.util.Constant.MOVIE_DB_NAME
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.messaging.FirebaseMessaging
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object DataSourceModule {

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
        Room.databaseBuilder(context, MovieDatabase::class.java, MOVIE_DB_NAME).build()

    @Singleton
    @Provides
    fun provideMovieApi(): MovieApi = ApiManager.getMovieApi()

    @Singleton
    @Provides
    fun provideTopicSubscriber(
        firebaseMessaging: FirebaseMessaging,
        @ApplicationScope applicationScope: CoroutineScope,
        @MainDispatcher mainDispatcher: CoroutineDispatcher
    ): TopicSubscriber = FcmTopicSubscriber(firebaseMessaging, applicationScope, mainDispatcher)

    @Singleton
    @Provides
    fun provideAuthStateUserDataSource(
        firebaseAuth: FirebaseAuth,
        fcmTokenUpdater: FcmTokenUpdater
    ): AuthStateUserDataSource = FirebaseAuthStateUserDataSource(firebaseAuth, fcmTokenUpdater)

    @Singleton
    @Provides
    fun provideUserEventDataSource(
        firestore: FirebaseFirestore,
        @IoDispatcher ioDispatcher: CoroutineDispatcher
    ): UserEventDataSource = FirestoreUserEventDataSource(firestore, ioDispatcher)
}
