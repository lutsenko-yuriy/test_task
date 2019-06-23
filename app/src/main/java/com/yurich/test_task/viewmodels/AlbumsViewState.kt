package com.yurich.test_task.viewmodels

import com.yurich.test_task.data.repository.Album

sealed class AlbumsViewState {

    data class ActualAlbums(val albums: List<Album>) : AlbumsViewState()

    data class NonActualAlbums(val albums: List<Album>) : AlbumsViewState()

    object Loading : AlbumsViewState()

    object Error : AlbumsViewState()

}