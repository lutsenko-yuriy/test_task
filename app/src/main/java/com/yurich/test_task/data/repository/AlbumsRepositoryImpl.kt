package com.yurich.test_task.data.repository

import com.yurich.test_task.data.database.AlbumsDaoDelegate
import com.yurich.test_task.data.network.AlbumsServiceDelegate

class AlbumsRepositoryImpl(
    private val serviceDelegate: AlbumsServiceDelegate,
    private val daoDelegate: AlbumsDaoDelegate
) : AlbumsRepository {

    override suspend fun refreshAlbumsData(): AlbumsDataState {
        val albumsFromNetwork = serviceDelegate.getAlbums()

        if (albumsFromNetwork.isNotEmpty()) {
            daoDelegate.refreshAlbums(albumsFromNetwork)
            return AlbumsDataState.FromNetwork(albumsFromNetwork)
        }

        val albumsFromLocalStorage = daoDelegate.getAlbums()
        if (albumsFromLocalStorage.isNotEmpty()) {
            return AlbumsDataState.FromDatabase(albumsFromLocalStorage)
        }

        return AlbumsDataState.FromNowhere
    }

}