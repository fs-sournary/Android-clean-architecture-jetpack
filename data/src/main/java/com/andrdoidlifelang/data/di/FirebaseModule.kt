package com.andrdoidlifelang.data.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.firestoreSettings
import com.google.firebase.installations.FirebaseInstallations
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.ktx.messaging
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object FirebaseModule {

    @Singleton
    @Provides
    fun provideFirebaseStore(): FirebaseFirestore = Firebase.firestore.apply {
        // Enable offline data
        firestoreSettings = firestoreSettings { isPersistenceEnabled = true }
    }

    @Singleton
    @Provides
    fun provideFirebaseInstallations(): FirebaseInstallations = FirebaseInstallations.getInstance()

    @Singleton
    @Provides
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    @Singleton
    @Provides
    fun provideFirebaseMessaging(): FirebaseMessaging = Firebase.messaging
}
