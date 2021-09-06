package com.picpay.desafio.android.application

import android.app.Application
import com.picpay.desafio.android.application.module.repositoryModule
import com.picpay.desafio.android.application.module.serviceModule
import com.picpay.desafio.android.application.module.viewModelModule
import com.picpay.desafio.android.database.MyDataBase
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.core.logger.Level

class MyApplication : Application() {
    var koinApplication: KoinApplication? = null

    companion object {
        var database: MyDataBase? = null
    }

    override fun onCreate() {
        super.onCreate()

        initKoin()
        initDatabase()
    }

    private fun initDatabase() {
        database = MyDataBase.getIntanceDatabase(this)
    }

    private fun initKoin() {
        stopKoin()
        koinApplication?.close()
        koinApplication = null

        this.koinApplication = startKoin {
            androidContext(this@MyApplication)
            androidLogger(Level.DEBUG)
            modules(
                listOf(
                    repositoryModule,
                    serviceModule,
                    viewModelModule
                )
            )
        }
    }
}