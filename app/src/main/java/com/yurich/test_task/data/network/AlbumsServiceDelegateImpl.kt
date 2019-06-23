package com.yurich.test_task.data.network

import com.yurich.test_task.data.repository.Album

class AlbumsServiceDelegateImpl(private val service: AlbumsService) : AlbumsServiceDelegate {

    override suspend fun getAlbums(): List<Album> {
        val response = service.getAlbums()
        return response.body()?.map { Album(it.userId, it.id, it.title) } ?: listOf()
    }

}