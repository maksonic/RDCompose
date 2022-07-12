package ru.maksonic.rdcompose.feature.player.service

import android.content.Context
import android.net.Uri
import androidx.media3.common.MediaItem
import androidx.media3.common.util.Util
import androidx.media3.datasource.DefaultDataSource
import androidx.media3.datasource.DefaultDataSourceFactory
import androidx.media3.exoplayer.source.MediaSource
import androidx.media3.exoplayer.source.ProgressiveMediaSource
import androidx.media3.extractor.DefaultExtractorsFactory

/**
 * @Author maksonic on 26.06.2022
 */

fun extractMediaSourceFromUri(context: Context, uri: String): MediaSource {
    return ProgressiveMediaSource.Factory(DefaultDataSource.Factory(context))
        .createMediaSource(MediaItem.fromUri(Uri.parse(uri)))
}

class MusicServiceConnection {
}