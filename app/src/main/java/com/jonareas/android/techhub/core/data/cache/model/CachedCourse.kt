package com.jonareas.android.techhub.core.data.cache.model

import androidx.recyclerview.widget.DiffUtil
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jonareas.android.techhub.utils.RANDOM_GENERATOR
import com.jonareas.android.techhub.utils.RANDOM_GENERATOR_SEED

@Entity(tableName = "courses")
data class CachedCourse constructor(
    @PrimaryKey(autoGenerate = true)
    val courseId: Int = 0,
    val name: String,
    val subject: String,
    val imageUrl: String,
    val thumbContentDesc: String,
    val description: String = "",
    val steps: Int,
    val step: Int,
    val instructorName : String = "John Doe",
    val instructorPhotoPath: String = "https://randomuser.me/api/portraits/men/${RANDOM_GENERATOR.nextInt(99)}.jpg?seed=$RANDOM_GENERATOR_SEED",
    var favorite : Boolean = false
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