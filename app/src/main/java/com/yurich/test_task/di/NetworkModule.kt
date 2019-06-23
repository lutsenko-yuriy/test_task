package com.yurich.test_task.di

import com.yurich.test_task.data.network.AlbumsService
import com.yurich.test_task.data.network.AlbumsServiceDelegate
import com.yurich.test_task.data.network.AlbumsServiceDelegateImpl
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {

    single<AlbumsService> {
        Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AlbumsService::class.java)
    }

    single<AlbumsServiceDelegate> { AlbumsServiceDelegateImpl(get()) }

}