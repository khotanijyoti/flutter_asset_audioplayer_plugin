package com.assestaudioplayer.assest_audio_player_plugin.notification

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.assestaudioplayer.assest_audio_player_plugin.AssestAudioPlayerPlugin


class NotificationActionReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val playerId = intent.getStringExtra(NotificationService.EXTRA_PLAYER_ID) ?: return
        val trackID = if (intent.getStringExtra(NotificationService.TRACK_ID) == null) "" else intent.getStringExtra(NotificationService.TRACK_ID)
        val player = AssestAudioPlayerPlugin.instance?.assetsAudioPlayer?.getPlayer(playerId) ?: return
        when (intent.action) {
             NotificationAction.ACTION_PREV -> player.prev()
             NotificationAction.ACTION_STOP -> {
                 player.askStop()
                 //NotificationManager(context).hideNotification()
             }
             NotificationAction.ACTION_NEXT -> player.next()
             NotificationAction.ACTION_TOGGLE -> {
                //val notificationAction = intent.getSerializableExtra(NotificationService.EXTRA_NOTIFICATION_ACTION) as NotificationAction.Show
                player.askPlayOrPause() //send it to flutter

                //update notif
                //NotificationManager(context).showNotification(playerId= playerId, audioMetas = notificationAction.audioMetas, isPlaying = player.)
            }
            NotificationAction.ACTION_SELECT -> {
                context.sendBroadcast(Intent(Intent.ACTION_CLOSE_SYSTEM_DIALOGS))
                context.startActivity(context.packageManager.getLaunchIntentForPackage(context.packageName)!!.apply {
                    action = NotificationAction.ACTION_SELECT
                    putExtra(NotificationService.TRACK_ID, trackID)
                })
            }
        }
    }
}