package com.assestaudioplayer.assest_audio_player_plugin

sealed class AssetAudioPlayerThrowable(val type: String, val t: Throwable) : Throwable() {
    class NetworkError(t: Throwable) : AssetAudioPlayerThrowable(type= "network", t= t)
    class UnreachableException(t: Throwable) : AssetAudioPlayerThrowable(type= "network", t= t)
    class PlayerError(t: Throwable) : AssetAudioPlayerThrowable(type= "player", t=t)
}
