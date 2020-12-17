package com.andrdoidlifelang.presentation.ext

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Build
import androidx.core.app.NotificationCompat
import com.andrdoidlifelang.presentation.R
import com.andrdoidlifelang.presentation.ui.MainActivity
import com.andrdoidlifelang.presentation.util.Constant.MOVIE_CHANNEL_ID
import com.andrdoidlifelang.presentation.util.Constant.NOTIFICATION_ID

fun NotificationManager.createChannel(channelId: String, channelName: String) {
    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) return
    val channel = NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_HIGH)
    channel.apply {
        enableLights(true)
        lightColor = Color.RED
        enableLights(true)
    }
    createNotificationChannel(channel)
}

fun NotificationManager.sendNotification(messageBody: String, applicationContext: Context) {
    val contentPendingIntentFlag = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        PendingIntent.FLAG_IMMUTABLE
    } else {
        PendingIntent.FLAG_UPDATE_CURRENT
    }
    val contentIntent = Intent(applicationContext, MainActivity::class.java)
    val contentPendingIntent = PendingIntent.getActivity(
        applicationContext,
        NOTIFICATION_ID,
        contentIntent,
        contentPendingIntentFlag
    )
    val movieImage =
        BitmapFactory.decodeResource(applicationContext.resources, R.drawable.ic_notification_logo)
    val bigPictureStyle = NotificationCompat.BigPictureStyle()
        .bigPicture(movieImage)
        .bigLargeIcon(null)
    val builder = NotificationCompat.Builder(applicationContext, MOVIE_CHANNEL_ID)
        .setSmallIcon(R.drawable.ic_notification_logo)
        .setContentTitle(applicationContext.getString(R.string.app_name))
        .setContentText(messageBody)
        .setStyle(bigPictureStyle)
        .setContentIntent(contentPendingIntent)
        .setPriority(NotificationCompat.PRIORITY_HIGH)
        .setAutoCancel(true)
    notify(NOTIFICATION_ID, builder.build())
}
