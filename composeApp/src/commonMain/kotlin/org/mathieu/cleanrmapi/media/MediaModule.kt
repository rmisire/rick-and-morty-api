package org.mathieu.cleanrmapi.media

import org.koin.dsl.module
import org.mathieu.cleanrmapi.media.AudioPlayer

val mediaModule = module {
    single { AudioPlayer(get()) }
}
