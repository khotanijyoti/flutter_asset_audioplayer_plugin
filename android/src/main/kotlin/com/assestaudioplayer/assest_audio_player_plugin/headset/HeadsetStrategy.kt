package com.assestaudioplayer.assest_audio_player_plugin.headset

enum class HeadsetStrategy {
    none,
    pauseOnUnplug,
    pauseOnUnplugPlayOnPlug;

    companion object  {
        fun from(s: String?) : HeadsetStrategy {
            return when(s){
                "pauseOnUnplug" -> pauseOnUnplug
                "pauseOnUnplugPlayOnPlug" -> pauseOnUnplugPlayOnPlug
                else -> none
            }
        }
    }
}