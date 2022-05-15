package com.jonareas.android.techhub.core.data.cache.model

import androidx.recyclerview.widget.DiffUtil
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "topics")
data class CachedTopic(
    @PrimaryKey(autoGenerate = true)
    val topicId: Int = 0,
    val name: String,
    val totalCourses: Int,
    val imageUrl: String
) : CachedEntity {
    companion object {
        val topicDiff = object : DiffUtil.ItemCallback<CachedTopic>() {
            override fun areItemsTheSame(oldItem: CachedTopic, newItem: CachedTopic) =
                oldItem.topicId == newItem.topicId

            override fun areContentsTheSame(oldItem: CachedTopic, newItem: CachedTopic) =
                oldItem == newItem
        }
    }
}