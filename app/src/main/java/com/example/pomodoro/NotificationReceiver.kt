package com.example.pomodoro

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import java.text.SimpleDateFormat
import java.util.*

class NotificationReceiver() : BroadcastReceiver() {

    override fun onReceive(p0: Context?, p1: Intent?) {
        if (p1 != null && p0 != null) {
            val requestCode = p1.getIntExtra("RequestCode", 0)
            val pi = PendingIntent.getActivity(
                p0,
                requestCode,
                p1,
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )
            val channelId = "default"

            val title = p0.getString(R.string.app_name)

            val currentTime = System.currentTimeMillis()
            val format = SimpleDateFormat("HH:mm:ss", Locale.JAPAN)
            val formattedTime = format.format(currentTime)

            val msg = "$formattedTime Next!"

            val channel =
                NotificationChannel(channelId, title, NotificationManager.IMPORTANCE_HIGH)
            channel.description = msg

            val notificationManager =
                (p0.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager)
            notificationManager.createNotificationChannel(channel)

            val builder = NotificationCompat.Builder(p0, channelId).apply {
                setSmallIcon(R.drawable.pomodoro)
                setContentTitle("Notification")
                setPriority(NotificationCompat.PRIORITY_HIGH)
                setContentIntent(pi)
                setAutoCancel(false)
            }

            val notificationManagerCompat = NotificationManagerCompat.from(p0)
            notificationManagerCompat.notify(R.string.app_name, builder.build())
        }
    }
}
