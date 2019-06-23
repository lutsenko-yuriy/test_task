package com.yurich.test_task.data.network
import com.google.gson.annotations.SerializedName

data class NetworkAlbum(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("userId")
    val userId: Int
)