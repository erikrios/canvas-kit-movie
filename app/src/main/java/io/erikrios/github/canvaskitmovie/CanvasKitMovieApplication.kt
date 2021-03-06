package io.erikrios.github.canvaskitmovie

import android.app.Application
import io.erikrios.github.canvaskitmovie.di.useCaseModule
import io.erikrios.github.canvaskitmovie.di.viewModelModule
import io.github.erikrios.canvaskitmovie.core.di.databaseModule
import io.github.erikrios.canvaskitmovie.core.di.networkModule
import io.github.erikrios.canvaskitmovie.core.di.repositoryModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class CanvasKitMovieApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@CanvasKitMovieApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}