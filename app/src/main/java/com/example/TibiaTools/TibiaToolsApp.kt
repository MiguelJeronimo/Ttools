package com.example.TibiaTools

import android.app.Application
import com.example.TibiaTools.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class TibiaToolsApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@TibiaToolsApp)
            modules(appModule)
        }
    }
}
