package com.yurich.test_task.di

import com.yurich.test_task.data.database.AlbumsDaoDelegate
import com.yurich.test_task.data.database.AlbumsDaoDelegateImpl
import com.yurich.test_task.data.database.AlbumsDatabase
import org.koin.dsl.module

val databaseModule = module {

    single {
        AlbumsDatabase.getDatabase(get())!!.albumsDao()
    }

    single<AlbumsDaoDelegate> { AlbumsDaoDelegateImpl(get()) }

}