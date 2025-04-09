package org.mathieu.cleanrmapi.media

import java.io.File
import javax.sound.sampled.AudioSystem

actual class AudioPlayer actual constructor(context: Any) {
    private val mediaItems = soundResList.map { File("composeResources/$it") }
    actual fun playSound(id: Int) {
        try {
            val audioInputStream = AudioSystem.getAudioInputStream(mediaItems[id])
            val clip = AudioSystem.getClip()
            clip.open(audioInputStream)
            clip.start()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
    actual fun release() {}
}
