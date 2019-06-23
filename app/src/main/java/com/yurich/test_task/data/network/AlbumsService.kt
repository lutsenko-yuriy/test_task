package com.yurich.test_task.data.network

import retrofit2.Response
import retrofit2.http.GET


interface AlbumsService {

    @GET("albums")
    suspend fun getAlbums(): Response<List<NetworkAlbum>>

}