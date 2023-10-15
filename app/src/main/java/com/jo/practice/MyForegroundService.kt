package com.jo.practice

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import kotlin.concurrent.thread


class MyForegroundService : Service() {
    private val notificationManager
        get() = getSystemService(NOTIFICATION_SERVICE) as NotificationManager

    override fun onCreate() {
        Log.e("TMPTEST", "onCreate")
        registerDefaultNotificationChannel()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        startForeground(NOTIFICATION_DOWNLOAD_ID, createDownloadingNotification(0))
        Log.e("TMPTEST", Thread.currentThread().name)
        thread {
            for (i in 1..100) {
                Log.e("TMPTEST", Thread.currentThread().name)
                Thread.sleep(100)
                updateProgres(i)
            }
            stopForeground(true)
            stopSelf()
            notificationManager.notify(NOTIFICATION_COMPLETE_ID, createCompleteNotification())
        }
        return START_STICKY
    }

    private fun updateProgres(progress: Int) {
        notificationManager.notify(NOTIFICATION_DOWNLOAD_ID, createDownloadingNotification(progress))
    }

    private fun createDownloadingNotification( progress: Int) =
        NotificationCompat.Builder(this, CHANNEL_ID).apply {
            setContentTitle("Download video...")
            setContentText("Wait!")
            setSmallIcon(R.drawable.ic_launcher_background)
            setVisibility(NotificationCompat.VISIBILITY_PUBLIC)

            setContentIntent(
                PendingIntent.getActivity(
                    this@MyForegroundService, 0, Intent(this@MyForegroundService, MainActivity::class.java).apply {
                        flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                    }, PendingIntent.FLAG_IMMUTABLE
                )
            )

            setProgress(100, progress, false)
        }.build()

    private fun createCompleteNotification() = NotificationCompat.Builder(this, CHANNEL_ID).apply {
        setContentTitle("Download complete!")
        setContentText("Nice 🚀")
        setSmallIcon(R.drawable.ic_launcher_background)
        setVisibility(NotificationCompat.VISIBILITY_PUBLIC)

        setContentIntent(
            PendingIntent.getActivity(
                this@MyForegroundService, 0, Intent(this@MyForegroundService, MainActivity::class.java).apply {
                    flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                }, PendingIntent.FLAG_IMMUTABLE
            )
        )
    }.build()

    private fun registerDefaultNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationManager.createNotificationChannel(createDefaultNotificationChannel())
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createDefaultNotificationChannel() =
        NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_LOW).apply {
            description = CHANNEL_DESCRIPTION
            this.setShowBadge(true)
            this.lockscreenVisibility = NotificationCompat.VISIBILITY_PUBLIC
        }

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    companion object {
        private const val NOTIFICATION_DOWNLOAD_ID = 1
        private const val NOTIFICATION_COMPLETE_ID = 2
        private const val CHANNEL_ID = "my_channel"
        private const val CHANNEL_NAME = "default"
        private const val CHANNEL_DESCRIPTION = "This is default notification channel"
    }
}