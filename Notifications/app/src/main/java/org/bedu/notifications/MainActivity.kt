package org.bedu.notifications

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RemoteViews
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
import org.bedu.notifications.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {ActivityMainBinding.inflate(layoutInflater)}

    companion object {

        const val CHANNEL_COURSES = "CHANNEL_COURSES"
        const val ACTION_RECEIVED = "ACTION_RECEIVED"
        const val CHANNEL_OTHERS = "OTROS"
        const val GRUPO_SIMPLE = "GRUPO_SIMPLE"

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        //Para android Oreo en adelante, s obligatorio registrar el canal de notificación
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            setNotificationChannel()
            setOthersChannel()
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

            btnExpandable.setOnClickListener {
                expandableNotification()
            }

            btnCustom.setOnClickListener {
                customNotification()
            }

            btnCancelNoti.setOnClickListener {
                cancelNotification()
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

    @RequiresApi(Build.VERSION_CODES.O)
    private fun setOthersChannel(){
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel(CHANNEL_OTHERS, getString(R.string.channel_others), importance).apply {
            description = getString(R.string.others_description)
        }

        val notificationManager: NotificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        notificationManager.createNotificationChannel(channel)
    }

    private fun simpleNotification(){
        val notification = NotificationCompat.Builder(this, CHANNEL_COURSES)
            .setSmallIcon(R.drawable.triforce) //seteamos el ícono de la push notification
            .setColor(ContextCompat.getColor(this, R.color.triforce)) //definimos el color del ícono y el título de la notificación
            .setContentTitle(getString(R.string.simple_title)) //seteamos el título de la notificación
            .setContentText(getString(R.string.simple_body)) //seteamos el cuerpo de la notificación
            .setPriority(NotificationCompat.PRIORITY_DEFAULT) //Ponemos una prioridad por defecto
            .setGroup(GRUPO_SIMPLE)
            .build()

        val notification2 = NotificationCompat.Builder(this, CHANNEL_COURSES)
            .setSmallIcon(R.drawable.triforce) //seteamos el ícono de la push notification
            .setColor(ContextCompat.getColor(this, R.color.triforce)) //definimos el color del ícono y el título de la notificación
            .setContentTitle(getString(R.string.simple_title_2)) //seteamos el título de la notificación
            .setContentText(getString(R.string.simple_body_2)) //seteamos el cuerpo de la notificación
            .setPriority(NotificationCompat.PRIORITY_DEFAULT) //Ponemos una prioridad por defecto
            .setGroup(GRUPO_SIMPLE)
            .build()

        val notification3 = NotificationCompat.Builder(this, CHANNEL_COURSES)
            .setSmallIcon(R.drawable.triforce) //seteamos el ícono de la push notification
            .setColor(ContextCompat.getColor(this, R.color.triforce)) //definimos el color del ícono y el título de la notificación
            .setContentTitle(getString(R.string.simple_title_3)) //seteamos el título de la notificación
            .setContentText(getString(R.string.simple_body_3)) //seteamos el cuerpo de la notificación
            .setPriority(NotificationCompat.PRIORITY_DEFAULT) //Ponemos una prioridad por defecto
            .setGroup(GRUPO_SIMPLE)
            .build()


        val summaryNotification = NotificationCompat.Builder(this@MainActivity, CHANNEL_OTHERS)
            .setSmallIcon(R.drawable.bedu_icon)
            .setGroup(GRUPO_SIMPLE)
            .setGroupSummary(true)
            .build()

        //lanzamos la notificación
        NotificationManagerCompat.from(this).run {
            notify(20, notification)
            notify(21, notification2)
            notify(22, notification3)
            notify(23, summaryNotification)


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
            notify(90, notification)
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
            notify(89, notification)
        }
    }

    private fun expandableNotification() {
        val notification = NotificationCompat.Builder(this, CHANNEL_COURSES)
            .setSmallIcon(R.drawable.triforce) //seteamos el ícono de la push notification
            .setColor(ContextCompat.getColor(this, R.color.triforce)) //definimos el color del ícono y el título de la notificación
            .setContentTitle(getString(R.string.simple_title)) //seteamos el título de la notificación
            .setContentText(getString(R.string.large_text)) //seteamos el cuerpo de la notificación
            .setLargeIcon(getDrawable(R.drawable.bedu)?.toBitmap())
            .setStyle(NotificationCompat.BigTextStyle()
                .bigText(getString(R.string.large_text)))
            .setPriority(NotificationCompat.PRIORITY_DEFAULT) //Ponemos una prioridad por defecto
            .build()

        NotificationManagerCompat.from(this).run {
            notify(42, notification)
        }
    }

    private fun customNotification() {
        val notificationLayout = RemoteViews(packageName,R.layout.notification_custom)
        val notificationLayoutExpanded = RemoteViews(packageName,R.layout.notification_custom_expanded)

        val notificaion = NotificationCompat.Builder(this, CHANNEL_OTHERS)
            .setSmallIcon(R.drawable.bedu_icon)
            .setStyle(NotificationCompat.DecoratedCustomViewStyle())
            .setCustomContentView(notificationLayout)
            .setCustomBigContentView(notificationLayoutExpanded)
            .build()

        NotificationManagerCompat.from(this).run {
            notify(876, notificaion)
        }

    }

    private fun cancelNotification() {
        NotificationManagerCompat.from(this).apply {
            cancelAll()
        }


    }
}