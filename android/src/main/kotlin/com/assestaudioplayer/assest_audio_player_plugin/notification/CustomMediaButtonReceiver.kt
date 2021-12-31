package com.assestaudioplayer.assest_audio_player_plugin.notification

import android.content.Context
import android.content.Intent
import android.util.Log

import androidx.annotation.Keep;
import androidx.media.session.MediaButtonReceiver

@Keep
class CustomMediaButtonReceiver : MediaButtonReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        try {
            super.onReceive(context, intent)
        } catch (e: Exception) {
            Log.e(javaClass.name, e.message ?: "unknown error")
        }
    }
}
