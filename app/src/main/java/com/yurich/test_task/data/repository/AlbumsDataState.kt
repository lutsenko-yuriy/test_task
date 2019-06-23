package com.yurich.test_task.data.repository

sealed class AlbumsDataState {

    data class FromNetwork(val albums: List<Album>) : AlbumsDataState()

    data class FromDatabase(val albums: List<Album>) : AlbumsDataState()

    object FromNowhere : AlbumsDataState()

}