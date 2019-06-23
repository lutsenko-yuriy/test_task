package com.yurich.test_task.data.database

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE

@Dao
abstract class AlbumsDao {

    @Query("SELECT * FROM albums")
    abstract suspend fun getAlbums(): List<LocalStorageAlbum>

    @Insert(onConflict = REPLACE)
    abstract suspend fun putAlbums(albums: List<LocalStorageAlbum>)

    @Delete
    abstract suspend fun deleteAlbums(albums: List<LocalStorageAlbum>)

    @Transaction
    open suspend fun replaceAlbums(albums: List<LocalStorageAlbum>) {
        deleteAlbums(getAlbums())
        putAlbums(albums)
    }

}