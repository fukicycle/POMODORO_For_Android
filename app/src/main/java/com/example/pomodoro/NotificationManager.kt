package com.example.pomodoro

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.widget.Toast
import java.util.*


class NotificationManager(private val context: Context) {

    @SuppressLint("ServiceCast")
    fun set() {
        val calendar: Calendar = Calendar.getInstance()
        calendar.timeInMillis = System.currentTimeMillis()
        calendar.add(Calendar.SECOND, 10)
        val intent = Intent(
            context,
            MainActivity::class.java
        )
        intent.putExtra("RequestCode", 1)

        val pending = PendingIntent.getBroadcast(
            context, 1, intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        // アラームをセットする

        // アラームをセットする
        val am = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

        am.setExact(
            AlarmManager.RTC_WAKEUP,
            calendar.timeInMillis, pending
        )

        // トーストで設定されたことをを表示
        Toast.makeText(
            context,
            "alarm start", Toast.LENGTH_SHORT
        ).show()
    }
}