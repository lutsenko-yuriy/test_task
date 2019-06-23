package com.yurich.test_task.data.repository

interface AlbumsRepository {

    suspend fun loadAlbumsData(): AlbumsDataStatus

}