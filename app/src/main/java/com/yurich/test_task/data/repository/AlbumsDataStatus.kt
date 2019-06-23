package com.yurich.test_task.data.repository

sealed class AlbumsDataStatus {

    data class FromNetwork(val albums: List<Album>) : AlbumsDataStatus()

    data class FromDatabase(val albums: List<Album>) : AlbumsDataStatus()

    object FromNowhere : AlbumsDataStatus()

}