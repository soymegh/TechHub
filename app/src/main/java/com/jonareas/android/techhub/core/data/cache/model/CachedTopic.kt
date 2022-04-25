package com.jonareas.android.techhub.core.data.cache.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "topics")
data class CachedTopic(
    val name: String,
    val courses: Int,
    val imageUrl: String,
    @PrimaryKey(autoGenerate = true)
    val topicId : Int = 0
) : CachedEntity