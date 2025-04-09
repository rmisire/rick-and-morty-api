package org.mathieu.cleanrmapi.media

expect class AudioPlayer(context: Any) {
    fun playSound(id: Int)
    fun release()
}

val soundResList = listOf(
    "files/effect_sound.mp3"
)
