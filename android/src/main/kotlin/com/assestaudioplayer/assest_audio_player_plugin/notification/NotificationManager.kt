package com.assestaudioplayer.assest_audio_player_plugin.notification

import android.content.Context
import android.content.Intent
import com.assestaudioplayer.assest_audio_player_plugin.AssestAudioPlayerPlugin

class NotificationManager(private val context: Context) {

    var closed = false

    fun showNotification(playerId: String, audioMetas: AudioMetas, isPlaying: Boolean, notificationSettings: NotificationSettings, stop: Boolean, durationMs: Long) {
        try {
            if (closed)
                return
            if (stop) {
                stopNotification()
            } else {
                context.startService(Intent(context, NotificationService::class.java).apply {
                    putExtra(NotificationService.EXTRA_NOTIFICATION_ACTION, NotificationAction.Show(
                            isPlaying = isPlaying,
                            audioMetas = audioMetas,
                            playerId = playerId,
                            notificationSettings = notificationSettings,
                            durationMs = durationMs
                    ))
                })
            }
            AssestAudioPlayerPlugin.instance?.assetsAudioPlayer?.registerLastPlayerWithNotif(playerId)
        } catch (t: Throwable) {
            t.printStackTrace()
        }
    }

    fun stopNotification() {
        try {
            context.startService(Intent(context, NotificationService::class.java).apply {
                putExtra(NotificationService.EXTRA_NOTIFICATION_ACTION, NotificationAction.Hide(
                ))
            })
        } catch (t: Throwable) {
            t.printStackTrace()
        }
    }

    fun hideNotificationService(definitively: Boolean = false) {
        try {
            //if remainingNotif == 0, stop
            context.stopService(Intent(context, NotificationService::class.java))
            closed = definitively
        } catch (t: Throwable) {
            t.printStackTrace()
        }
    }
}