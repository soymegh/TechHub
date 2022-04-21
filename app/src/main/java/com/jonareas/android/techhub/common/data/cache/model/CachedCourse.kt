package com.jonareas.android.techhub.common.data.cache.model

import com.jonareas.android.techhub.utils.RANDOM_GENERATOR
import com.jonareas.android.techhub.utils.RANDOM_GENERATOR_SEED


data class CachedCourse(
    val id: Int,
    val name: String,
    val subject: String,
    val thumbUrl: String,
    val thumbContentDesc: String,
    val description: String = "",
    val steps: Int,
    val step: Int,
    val status : Boolean = RANDOM_GENERATOR.nextBoolean(),
    val gender : String = if(status) "men" else "women",
    val instructor: String = "https://randomuser.me/api/portraits/${gender}/${id + 27}.jpg?seed=$RANDOM_GENERATOR_SEED",
)