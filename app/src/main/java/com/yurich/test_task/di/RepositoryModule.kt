package com.yurich.test_task.di

import com.yurich.test_task.data.repository.AlbumsRepository
import com.yurich.test_task.data.repository.AlbumsRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {

    single<AlbumsRepository> { AlbumsRepositoryImpl(get(), get()) }

}