import 'dart:async';


import 'package:assest_audio_player_plugin/playable.dart';
import 'package:path_provider/path_provider.dart';

typedef CachePathProvider = Future<String> Function(Audio audio, String key);
typedef AudioKeyTransformer = Future<String> Function(Audio audio);

class AssetsAudioPlayerCache {
  AssetsAudioPlayerCache({
    required this.cachePathProvider,
    required this.audioKeyTransformer,
  });

  final CachePathProvider cachePathProvider;
  final AudioKeyTransformer audioKeyTransformer;
}

String removeHttpSpecialCharsFromStrings(String from) {
  return from
      .replaceAll(RegExp(r'/'), '_')
      .replaceAll(RegExp(r':'), '_')
      .replaceAll(RegExp(r'%'), '_');
}

AssetsAudioPlayerCache defaultAssetsAudioPlayerCache = AssetsAudioPlayerCache(
  cachePathProvider: (audio, key) async {
    final directory = await getTemporaryDirectory();
    final dirPath = directory.path;
    return '$dirPath/$key';
  },
  audioKeyTransformer: (audio) async {
    return removeHttpSpecialCharsFromStrings(audio.path);
  },
);
