package com.yurich.test_task.data.network

import com.yurich.test_task.data.repository.Album
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext
import java.net.UnknownHostException

class AlbumsServiceDelegateImpl(private val service: AlbumsService) : AlbumsServiceDelegate {

    override suspend fun getAlbums(): List<Album> = withContext(IO) {
        return@withContext try {
            service.getAlbums().body()?.map { Album(it.userId, it.id, it.title) } ?: listOf()
        } catch (e: Exception) {
            @Suppress("RemoveExplicitTypeArguments")
            emptyList<Album>()
        }
    }

}