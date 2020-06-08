package com.relaxingproject.util

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Build
import com.relaxingproject.MainActivity
import com.relaxingproject.R

class NotificationUt {
    companion object{
        lateinit var notificationManager: NotificationManager
        lateinit var notificationChannel: NotificationChannel
        lateinit var builder : Notification.Builder
        private val channelId = "com.relaxingproject"
        private val description = "Test Notification"


        fun notifFun(context: Context){
            notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            val intent = Intent(context,MainActivity::class.java)
            val pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)


            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                notificationChannel =
                    NotificationChannel(channelId, description, NotificationManager.IMPORTANCE_HIGH)
                notificationChannel.enableLights(true)
                notificationChannel.lightColor = Color.GREEN
                notificationChannel.enableVibration(false)
                notificationManager.createNotificationChannel(notificationChannel)

                builder  = Notification.Builder(context, channelId)
                    .setContentTitle("RelaxingApp")
                    .setContentText("Don't forget to log and rate your day!")
                    .setSmallIcon(R.drawable.meditate)
                    .setLargeIcon(BitmapFactory.decodeResource(context.resources, R.drawable.meditate))
                    .setContentIntent(pendingIntent)
            } else{

                builder  = Notification.Builder(context)
                    .setContentTitle("RelaxingApp")
                    .setContentText("Don't forget to log and rate your day!")
                    .setSmallIcon(R.drawable.meditate)
                    .setLargeIcon(BitmapFactory.decodeResource(context.resources, R.drawable.meditate))
                    .setContentIntent(pendingIntent)
            }
            builder.build().flags =
                builder.build().flags or Notification.FLAG_AUTO_CANCEL
            notificationManager.notify(1234, builder.build())
        }
    }
}