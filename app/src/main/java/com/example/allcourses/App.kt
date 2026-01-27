package com.example.allcourses

import android.app.Application
import android.os.Build
import androidx.annotation.RequiresApi
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class App : Application()  {

    @RequiresApi(Build.VERSION_CODES.VANILLA_ICE_CREAM)
    override fun onCreate() {
        super.onCreate()

        _instance  = this

        startKoin {
            androidContext(this@App)
            modules(
//                apiModule,
//                dbModule,
//                repoModule,
//                viewModelModule
            )
        }
    }

    companion object {
        private var _instance: App? = null
        val instance: App
            get() = _instance!!
    }
}