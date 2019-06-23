package com.yurich.test_task.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [LocalStorageAlbum::class], version = 1)
abstract class AlbumsDatabase : RoomDatabase() {

    abstract fun albumsDao(): AlbumsDao

    companion object {

        @Volatile
        private var INSTANCE: AlbumsDatabase? = null

        fun getDatabase(context: Context): AlbumsDatabase? {
            if (INSTANCE == null) {
                synchronized(AlbumsDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        AlbumsDatabase::class.java, "albums.db"
                    ).build()
                }
            }
            return INSTANCE
        }

    }

}