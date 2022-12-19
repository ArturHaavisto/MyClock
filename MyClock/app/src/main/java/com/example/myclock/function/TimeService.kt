package com.example.myclock.function

import android.app.PendingIntent
import android.app.PendingIntent.FLAG_IMMUTABLE
import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import com.example.myclock.MainActivity
import com.example.myclock.R

class MyForegroundService : Service() {
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        //val input = intent?.getStringExtra(Constants.inputExtra)
        val notificationIntent = Intent(this, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, FLAG_IMMUTABLE)
        Log.e("jotain", "jotain")
        // Create a notification using NotificationCompat.Builder
        val notification = NotificationCompat.Builder(this, "testchannelid")
            .setContentTitle("My Foreground Service")
            .setContentText("Running in the foreground")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentIntent(pendingIntent)
            .build()

        // Put the service in the foreground and display the notification
        startForeground(1, notification)

        // Return START_STICKY to ensure that the service is restarted if it is stopped
        return START_STICKY
    }

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }
}