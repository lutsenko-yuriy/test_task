package com.yurich.test_task.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "albums")
data class LocalStorageAlbum(
    @PrimaryKey
    val id: Int,
    val userId: Int,
    val title: String
)