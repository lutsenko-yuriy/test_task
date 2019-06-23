package com.yurich.test_task

import android.app.Application
import com.yurich.test_task.di.databaseModule
import com.yurich.test_task.di.networkModule
import com.yurich.test_task.di.repositoryModule
import com.yurich.test_task.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class TestApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@TestApplication)

            modules(listOf(
                networkModule,
                databaseModule,
                repositoryModule,
                viewModelModule
            ))
        }
    }
}