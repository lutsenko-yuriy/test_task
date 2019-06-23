package com.yurich.test_task.data.repository

interface AlbumsRepository {

    suspend fun refreshAlbumsData(): AlbumsDataState

}