package com.yurich.test_task.data.repository

class AlbumsRepositoryImpl : AlbumsRepository {

    override suspend fun loadAlbumsData(): AlbumsDataStatus {
        return AlbumsDataStatus.FromNowhere
    }

}