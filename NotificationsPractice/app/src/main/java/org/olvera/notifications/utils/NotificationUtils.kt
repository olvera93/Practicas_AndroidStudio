package org.olvera.notifications.utils

import android.app.Application
import android.app.NotificationManager
import android.content.Context
import android.graphics.BitmapFactory
import androidx.core.app.NotificationCompat
import org.olvera.notifications.R

//Notification ID.
private val NOTIFICATION_ID = 0
private val REQUEST_CODE = 0
private val FLAGS = 0


fun NotificationManager.sendNotification(messageBody: String, applicationContext: Context) {

    val builder = NotificationCompat.Builder(
        applicationContext,
        applicationContext.getString(R.string.egg_notification_channel_id)
    )

    val eggImage = android.graphics.BitmapFactory.decodeResource(
        applicationContext.resources,
        R.drawable.ic_launcher_background
    )
}

fun NotificationManager.cancelNotifications(){
    cancelAll()
}

