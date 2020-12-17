package com.andrdoidlifelang.presentation.service

import android.app.NotificationManager
import androidx.core.content.ContextCompat
import com.andrdoidlifelang.presentation.ext.sendNotification
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import timber.log.Timber

class MovieFirebaseCloudMessagingService : FirebaseMessagingService() {

    private val notificationManager: NotificationManager by lazy {
        ContextCompat.getSystemService(
            applicationContext, NotificationManager::class.java
        ) as NotificationManager
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Timber.d("New firebase cloud messaging token: $token")
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)
        // If the message contains a notification payload, the app is in the foreground.
        remoteMessage.notification?.let {
            notificationManager.sendNotification(it.body as String, applicationContext)
        }
    }
}
