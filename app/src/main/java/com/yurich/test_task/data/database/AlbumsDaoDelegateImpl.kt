package com.yurich.test_task.data.database

import com.yurich.test_task.data.repository.Album
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext

class AlbumsDaoDelegateImpl(private val dao: AlbumsDao) : AlbumsDaoDelegate {

    override suspend fun getAlbums(): List<Album> = withContext(IO) {
        dao.getAlbums().map { Album(it.userId, it.id, it.title) }
    }

    override suspend fun refreshAlbums(albums: List<Album>) = withContext(IO) {
        dao.replaceAlbums(albums.map { LocalStorageAlbum(it.id, it.userId, it.title) })
    }

}