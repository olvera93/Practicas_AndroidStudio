package org.bedu.notifications

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class NotificationReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {

        if (intent?.action == MainActivity.ACTION_RECEIVED) {
            Toast.makeText(context, "Tarea ejecutada! :)", Toast.LENGTH_SHORT).show()
        }
    }
}