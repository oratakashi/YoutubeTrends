package com.oratakashi.youtube.core

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.oratakashi.youtube.core.di.CoreModule
import com.oratakashi.youtube.presentation.di.PresentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        AppCompatDelegate.setDefaultNightMode(
            AppCompatDelegate.MODE_NIGHT_YES
        )
        startKoin {
            androidLogger(Level.INFO)
            androidContext(this@App)
            modules(listOf(
                CoreModule.networkModule,
                CoreModule.repositoryModule,
                CoreModule.domainModule
            ))
            modules(listOf(
                PresentationModule.viewModelModule
            ))
        }
    }
}