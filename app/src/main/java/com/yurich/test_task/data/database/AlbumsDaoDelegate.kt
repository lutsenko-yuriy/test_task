package com.yurich.test_task.data.database

import com.yurich.test_task.data.repository.Album

interface AlbumsDaoDelegate {

    suspend fun getAlbums(): List<Album>

    suspend fun refreshAlbums(albums: List<Album>): List<Album>

}