package com.yurich.test_task.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "albums")
data class LocalStorageAlbum(
    @PrimaryKey
    val userId: Int,
    val id: Int,
    val title: String
)