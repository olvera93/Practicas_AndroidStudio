package org.bedu.notifications

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import org.bedu.notifications.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {ActivityMainBinding.inflate(layoutInflater)}

    companion object {
        const val CHANNEL_COURSES = "CHANNEL_COURSES"

        const val ACTION_RECEIVED = "ACTION_RECEIVED"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        //Para android Oreo en adelante, s obligatorio registrar el canal de notificación
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            setNotificationChannel()
        }

        binding.run {
            btnNotify.setOnClickListener {
                simpleNotification()
            }

            btnActionNotify.setOnClickListener {
                touchNotification()
            }

            btnNotifyWithBtn.setOnClickListener {
                actionNotication()
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun setNotificationChannel() {
        val name = getString(R.string.channel_courses)
        val descriptionText = getString(R.string.courses_description)
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel(CHANNEL_COURSES, name, importance).apply {
            description = descriptionText
        }

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        notificationManager.createNotificationChannel(channel)
    }

    private fun simpleNotification(){
        val notification = NotificationCompat.Builder(this, CHANNEL_COURSES)
            .setSmallIcon(R.drawable.triforce) //seteamos el ícono de la push notification
            .setColor(ContextCompat.getColor(this, R.color.triforce)) //definimos el color del ícono y el título de la notificación
            .setContentTitle(getString(R.string.simple_title)) //seteamos el título de la notificación
            .setContentText(getString(R.string.simple_body)) //seteamos el cuerpo de la notificación
            .setPriority(NotificationCompat.PRIORITY_DEFAULT) //Ponemos una prioridad por defecto
            .build()

        //lanzamos la notificación
        NotificationManagerCompat.from(this).run {
            notify(20, notification)
        }
    }

    private fun touchNotification(){
        //Un PendingIntent para dirigirnos a una actividad pulsando la notificación
        val intent = Intent(this, BeduActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }

        val pendingIntent = PendingIntent.getActivity(this, 0, intent, 0)
        val notification = NotificationCompat.Builder(this, CHANNEL_COURSES)
            .setSmallIcon(R.drawable.bedu_icon) //seteamos el ícono de la push notification
            .setColor(ContextCompat.getColor(this, R.color.triforce)) //definimos el color del ícono y el título de la notificación
            .setContentTitle(getString(R.string.action_title)) //seteamos el título de la notificación
            .setContentText(getString(R.string.action_body)) //seteamos el cuerpo de la notificación
            .setPriority(NotificationCompat.PRIORITY_DEFAULT) //Ponemos una prioridad por defecto
            .setContentIntent(pendingIntent) //se define aquí el content intend
            .setAutoCancel(true) //la notificación desaparece al dar click sobre ella
            .build()

        NotificationManagerCompat.from(this).run {
            notify(20, notification)
        }
    }

    private fun actionNotication() {

        val acceptIntent = Intent(this, NotificationReceiver::class.java).apply {
            action = ACTION_RECEIVED
        }

        val pendingIntent = PendingIntent.getBroadcast(this, 0, acceptIntent, 0)

        val notification = NotificationCompat.Builder(this, CHANNEL_COURSES)
            .setSmallIcon(R.drawable.bedu_icon) //seteamos el ícono de la push notification
            .setColor(ContextCompat.getColor(this, R.color.triforce)) //definimos el color del ícono y el título de la notificación
            .setContentTitle(getString(R.string.button_title)) //seteamos el título de la notificación
            .setContentText(getString(R.string.button_body)) //seteamos el cuerpo de la notificación
            .setPriority(NotificationCompat.PRIORITY_DEFAULT) //Ponemos una prioridad por defecto
            .addAction(R.drawable.bedu_icon, getString(R.string.button_text), pendingIntent) //agregamos la acción
            .build()

        NotificationManagerCompat.from(this).run {
            notify(20, notification)
        }
    }
}