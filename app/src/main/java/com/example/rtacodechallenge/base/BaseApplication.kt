package com.example.rtacodechallenge.base

import android.app.Application
import com.example.rtacodechallenge.di.appComponent
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        koinConfig()
    }

    private fun koinConfig() {
        startKoin {
            androidLogger()
            androidContext(this@BaseApplication)
            androidFileProperties()
            modules(appComponent)
        }
    }
}