package com.yurich.test_task.data.network

import com.yurich.test_task.data.repository.Album

interface AlbumsServiceDelegate {

    suspend fun getAlbums(): List<Album>

}