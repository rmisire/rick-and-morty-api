package org.mathieu.cleanrmapi

import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.mathieu.cleanrmapi.data.databaseModule
import org.mathieu.cleanrmapi.data.remoteModule
import org.mathieu.cleanrmapi.data.repositoriesModule
import org.mathieu.cleanrmapi.media.mediaModule


fun initKoin(config: KoinAppDeclaration? = null) =
    startKoin {
        config?.invoke(this)
        modules(
            remoteModule,
            repositoriesModule,
            mediaModule,
            databaseModule,
            org.mathieu.cleanrmapi.data.dataStoreModule,
            org.mathieu.cleanrmapi.data.databaseBuilderModule
        )
    }

