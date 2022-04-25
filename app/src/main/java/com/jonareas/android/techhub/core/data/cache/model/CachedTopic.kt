package com.jonareas.android.techhub.core.data.cache.model

import androidx.recyclerview.widget.DiffUtil
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "topics")
data class CachedTopic(
    val name: String,
    val courses: Int,
    val imageUrl: String,
    @PrimaryKey(autoGenerate = true)
    val topicId: Int = 0,
) : CachedEntity {
    companion object {
        val topicDiff = object : DiffUtil.ItemCallback<CachedTopic>() {
            override fun areItemsTheSame(oldItem: CachedTopic, newItem: CachedTopic) =
                oldItem.name == newItem.name

            override fun areContentsTheSame(oldItem: CachedTopic, newItem: CachedTopic) =
                oldItem == newItem
        }
    }
}