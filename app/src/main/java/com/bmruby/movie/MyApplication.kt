package com.bmruby.movie

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import com.bmruby.movie.di.appModule
import com.bmruby.movie.di.dbModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext
import org.koin.core.logger.Level


class MyApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
        GlobalContext.startKoin {
            androidLogger(level = Level.ERROR)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    dbModule,
                    appModule,
                )
               )
        }
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }
    companion object{
        lateinit var appContext: Context
    }

}