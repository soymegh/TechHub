package com.jonareas.android.techhub.core.data.cache.model

import androidx.recyclerview.widget.DiffUtil
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jonareas.android.techhub.utils.RANDOM_GENERATOR
import com.jonareas.android.techhub.utils.RANDOM_GENERATOR_SEED

@Entity(tableName = "courses")
data class CachedCourse(
    @PrimaryKey(autoGenerate = true)
    val courseId: Int,
    val name: String,
    val subject: String,
    val thumbUrl: String,
    val thumbContentDesc: String,
    val description: String = "",
    val steps: Int,
    val step: Int,
    val status : Boolean = RANDOM_GENERATOR.nextBoolean(),
    val gender : String = if(status) "men" else "women",
    val instructor: String = "https://randomuser.me/api/portraits/${gender}/${courseId + 27}.jpg?seed=$RANDOM_GENERATOR_SEED"
) : CachedEntity {
    companion object {
        val courseDiff = object : DiffUtil.ItemCallback<CachedCourse>() {
            override fun areItemsTheSame(oldItem: CachedCourse, newItem: CachedCourse) =
                oldItem.courseId == newItem.courseId

            override fun areContentsTheSame(oldItem: CachedCourse, newItem: CachedCourse) =
                oldItem == newItem
        }
    }
}