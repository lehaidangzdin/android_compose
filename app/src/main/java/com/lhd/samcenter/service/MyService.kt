package com.lhd.samcenter.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import com.lhd.samcenter.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class MyService : Service() {
    private val serviceScope = CoroutineScope(Dispatchers.IO)
    override fun onCreate() {
        super.onCreate()
        Log.e(TAG, "onCreate: ")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        serviceScope.launch {
            pushNotification()
            Log.e(TAG, "onStartCommand: start delay")
            delay(5000)
            Log.e(TAG, "onStartCommand: dasdasdas")
        }
        return START_STICKY

    }

    private fun pushNotification() {
        val channel = NotificationChannel(
            CHANNEL_ID,
            "PennSkanvTicChannel",
            NotificationManager.IMPORTANCE_MAX
        )
        channel.description = "PennSkanvTic channel for foreground service notification"

        val notificationManager =
            getSystemService<NotificationManager>(NotificationManager::class.java)
        notificationManager.createNotificationChannel(channel)
        val notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("My Service")
            .setWhen(System.currentTimeMillis())
            .setShowWhen(true)
            .setContentText("Service is running")
            .setSmallIcon(R.drawable.ic_notification)
            .build()

        startForeground(NOTIFICATION_ID, notification)
    }

    override fun onDestroy() {
        super.onDestroy()
        serviceScope.cancel()
        Log.e(TAG, "onDestroy: ")
    }

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    override fun onUnbind(intent: Intent?): Boolean {
        return super.onUnbind(intent)
    }

    companion object {
        const val TAG = "MyService"
        const val CHANNEL_ID = "MyServiceChannel"
        const val NOTIFICATION_ID = 1
    }
}