
import 'dart:async';

import 'package:flutter/services.dart';

class AssestAudioPlayerPlugin {
  static const MethodChannel _channel = MethodChannel('assest_audio_player_plugin.github.in');

  static Future<String?> get platformVersion async {
    final String? version = await _channel.invokeMethod('getPlatformVersion');
    return version;
  }

  static Future<String?> get something async {
    final String? version = await _channel.invokeMethod('getSomething');
    return version;
  }

}
