package com.yurich.test_task.data.repository

import com.yurich.test_task.data.database.AlbumsDaoDelegate
import com.yurich.test_task.data.network.AlbumsServiceDelegate
import kotlinx.coroutines.Dispatchers.Default
import kotlinx.coroutines.withContext

class AlbumsRepositoryImpl(
    private val serviceDelegate: AlbumsServiceDelegate,
    private val daoDelegate: AlbumsDaoDelegate
) : AlbumsRepository {

    override suspend fun refreshAlbumsData(): AlbumsDataState = withContext(Default) {
        val albumsFromNetwork = getAlbumsFromNetwork()
        if (albumsFromNetwork.isNotEmpty()) {
            return@withContext AlbumsDataState.FromNetwork(albumsFromNetwork)
        }

        val albumsFromLocalStorage = albumsFromDatabase()
        if (albumsFromLocalStorage.isNotEmpty()) {
            return@withContext AlbumsDataState.FromDatabase(albumsFromLocalStorage)
        }

        return@withContext AlbumsDataState.FromNowhere
    }

    private suspend fun getAlbumsFromNetwork(): List<Album> {
        val albumsFromNetwork = serviceDelegate.getAlbums()

        if (albumsFromNetwork.isNotEmpty()) {
            daoDelegate.refreshAlbums(albumsFromNetwork)
        }

        return albumsFromNetwork
    }

    private suspend fun albumsFromDatabase() = daoDelegate.getAlbums()
}