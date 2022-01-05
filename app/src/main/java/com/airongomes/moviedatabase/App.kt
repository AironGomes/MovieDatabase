package com.airongomes.moviedatabase

import android.app.Application
import com.airongomes.moviedatabase.di.defaultModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(
                listOf(defaultModule)
            )
        }
    }
}