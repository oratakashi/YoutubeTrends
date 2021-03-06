package com.oratakashi.youtube.core

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.oratakashi.youtube.core.di.CoreModule
import com.oratakashi.youtube.core.di.PresentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.core.logger.Level

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        AppCompatDelegate.setDefaultNightMode(
            AppCompatDelegate.MODE_NIGHT_YES
        )
        startKoin {
            androidLogger(
                when (BuildConfig.DEBUG) {
                    true -> Level.INFO
                    false -> Level.NONE
                }
            )
            androidContext(this@App)
            modules(
                listOf(
                    CoreModule.networkModule,
                    CoreModule.repositoryModule,
                    CoreModule.domainModule
                )
            )
            modules(PresentationModule.viewModelModule)
        }
    }

    override fun onTerminate() {
        super.onTerminate()
        stopKoin()
    }
}