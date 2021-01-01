package com.oratakashi.youtube.di

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.oratakashi.youtube.core.di.CoreModule
import dagger.hilt.android.HiltAndroidApp
import org.koin.android.ext.koin.androidContext
import org.koin.core.Koin
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        AppCompatDelegate.setDefaultNightMode(
            AppCompatDelegate.MODE_NIGHT_YES
        )
        startKoin {
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