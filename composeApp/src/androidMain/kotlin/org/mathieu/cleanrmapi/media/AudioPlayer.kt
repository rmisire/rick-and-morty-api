package org.mathieu.cleanrmapi.media

import android.content.Context
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import cleanrmapiudf.composeapp.generated.resources.Res
import org.jetbrains.compose.resources.ExperimentalResourceApi

@OptIn(ExperimentalResourceApi::class)
actual class AudioPlayer actual constructor(context: Any) {
    private val ctx = context as Context
    private val mediaPlayer = ExoPlayer.Builder(ctx).build()
    private val mediaItems = soundResList.map { MediaItem.fromUri(Res.getUri(it)) }
    init {
        mediaPlayer.prepare()
    }
    actual fun playSound(id: Int) {
        mediaPlayer.setMediaItem(mediaItems[id])
        mediaPlayer.play()
    }
    actual fun release() {
        mediaPlayer.release()
    }
}
