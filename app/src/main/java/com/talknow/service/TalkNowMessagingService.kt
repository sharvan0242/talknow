package com.talknow.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.talknow.MainActivity
import com.talknow.R
import timber.log.Timber

class TalkNowMessagingService : FirebaseMessagingService() {

    private val notificationManager by lazy {
        getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)

        Timber.d("Message received from: ${remoteMessage.from}")

        // Handle notification
        remoteMessage.notification?.let {
            sendNotification(
                title = it.title ?: "TalkNow",
                body = it.body ?: "You have a new call",
                priority = remoteMessage.priority
            )
        }

        // Handle data payload
        if (remoteMessage.data.isNotEmpty()) {
            val callType = remoteMessage.data["call_type"] ?: ""
            when (callType) {
                "incoming_call" -> handleIncomingCall(remoteMessage.data)
                "call_ended" -> handleCallEnded(remoteMessage.data)
                else -> Timber.w("Unknown call type: $callType")
            }
        }
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Timber.d("FCM token refreshed: $token")
        // Save token to Firebase for user
    }

    private fun handleIncomingCall(data: Map<String, String>) {
        val callerId = data["caller_id"] ?: ""
        val callerName = data["caller_name"] ?: "Unknown User"
        Timber.d("Incoming call from: $callerName ($callerId)")

        sendNotification(
            title = "Incoming Call",
            body = "$callerName is calling...",
            priority = NotificationCompat.PRIORITY_MAX,
            callerId = callerId
        )
    }

    private fun handleCallEnded(data: Map<String, String>) {
        val duration = data["duration"] ?: "0"
        Timber.d("Call ended. Duration: $duration seconds")

        sendNotification(
            title = "Call Ended",
            body = "Call duration: $duration seconds"
        )
    }

    private fun sendNotification(
        title: String,
        body: String,
        priority: Int = NotificationCompat.PRIORITY_HIGH,
        callerId: String = ""
    ) {
        val channelId = "call_notifications"
        createNotificationChannel(channelId)

        val intent = Intent(this, MainActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            if (callerId.isNotEmpty()) {
                putExtra("caller_id", callerId)
                putExtra("incoming_call", true)
            }
        }

        val pendingIntent = PendingIntent.getActivity(
            this,
            0,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val notification = NotificationCompat.Builder(this, channelId)
            .setSmallIcon(R.drawable.ic_notification)
            .setContentTitle(title)
            .setContentText(body)
            .setAutoCancel(true)
            .setContentIntent(pendingIntent)
            .setPriority(priority)
            .setCategory(NotificationCompat.CATEGORY_CALL)
            .build()

        notificationManager.notify(NOTIFICATION_ID, notification)
        Timber.d("Notification sent: $title - $body")
    }

    private fun createNotificationChannel(channelId: String) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Call Notifications"
            val descriptionText = "Notifications for incoming calls"
            val importance = NotificationManager.IMPORTANCE_MAX
            val channel = NotificationChannel(channelId, name, importance).apply {
                description = descriptionText
                enableVibration(true)
                enableLights(true)
            }
            notificationManager.createNotificationChannel(channel)
            Timber.d("Notification channel created: $channelId")
        }
    }

    companion object {
        private const val NOTIFICATION_ID = 1001
    }
}
