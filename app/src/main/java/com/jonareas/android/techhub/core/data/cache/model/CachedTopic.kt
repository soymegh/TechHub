package com.jonareas.android.techhub.core.data.cache.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CachedTopic(
    @PrimaryKey(autoGenerate = true)
    val topicId : Int = 0,
    val name: String,
    val courses: Int,
    val imageUrl: String
)