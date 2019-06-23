package com.yurich.test_task.data.network

import com.yurich.test_task.data.repository.Album
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext

class AlbumsServiceDelegateImpl(private val service: AlbumsService) : AlbumsServiceDelegate {

    override suspend fun getAlbums(): List<Album> = withContext(IO) {
        service.getAlbums().body()?.map { Album(it.userId, it.id, it.title) } ?: listOf()
    }

}