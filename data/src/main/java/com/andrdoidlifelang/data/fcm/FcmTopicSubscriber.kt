package com.andrdoidlifelang.data.fcm

import com.andrdoidlifelang.data.di.ApplicationScope
import com.andrdoidlifelang.data.di.MainDispatcher
import com.andrdoidlifelang.data.ext.suspendAndWait
import com.andrdoidlifelang.data.util.Constant.FCM_CHARACTER_TOPIC
import com.andrdoidlifelang.data.util.Constant.FCM_MOVIE_UPDATE_TOPIC
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import timber.log.Timber

class FcmTopicSubscriber(
    private val firebaseMessaging: FirebaseMessaging,
    @ApplicationScope private val applicationScope: CoroutineScope,
    @MainDispatcher private val mainDispatcher: CoroutineDispatcher
) : TopicSubscriber {

    override fun subscribeToMovieUpdates() {
        applicationScope.launch(mainDispatcher) {
            try {
                firebaseMessaging.subscribeToTopic(FCM_MOVIE_UPDATE_TOPIC).suspendAndWait()
            } catch (e: Exception) {
                Timber.d("Error while subscribing to movie updates topic")
            }
        }
    }

    override fun subscribeToCharacters() {
        applicationScope.launch(mainDispatcher) {
            try {
                firebaseMessaging.subscribeToTopic(FCM_CHARACTER_TOPIC).suspendAndWait()
            } catch (e: Exception) {
                Timber.d("Error while subscribing to character topic")
            }
        }
    }

    override fun unsubscribeToCharacters() {
        applicationScope.launch(mainDispatcher) {
            try {
                firebaseMessaging.unsubscribeFromTopic(FCM_CHARACTER_TOPIC).suspendAndWait()
            } catch (e: Exception) {
                Timber.d("Error while unsubscribing to character topic")
            }
        }
    }
}
