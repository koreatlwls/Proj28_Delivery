package com.example.proj28_delivery

import android.app.Application
import com.example.proj28_delivery.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class TrackerApplication : Application(){

    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidContext(this@TrackerApplication)
            modules(appModule)
        }
    }
}