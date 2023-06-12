package com.example.postreader.app

import android.app.Application
import com.example.postreader.model.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class PostApp: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidContext(this@PostApp)
            modules(listOf(appModule))
        }
    }
}
