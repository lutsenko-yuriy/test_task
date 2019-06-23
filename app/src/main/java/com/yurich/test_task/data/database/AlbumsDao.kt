package com.yurich.test_task.data.database

import androidx.room.*

@Dao
abstract class AlbumsDao {

    @Query("SELECT * FROM albums")
    abstract suspend fun getAlbums(): List<LocalStorageAlbum>

    @Insert
    abstract suspend fun putAlbums(albums: List<LocalStorageAlbum>)

    @Delete
    abstract suspend fun deleteAlbums()

    @Transaction
    suspend fun replaceAlbums(albums: List<LocalStorageAlbum>) {
        deleteAlbums()
        putAlbums(albums)
    }

}