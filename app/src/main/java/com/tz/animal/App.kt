package com.tz.animal

import android.app.Application
import com.tz.animal.di.DI_MODULES
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        initializeKoin()
    }

    private fun initializeKoin() = startKoin {
        androidContext(applicationContext)
        modules(DI_MODULES)
    }
}