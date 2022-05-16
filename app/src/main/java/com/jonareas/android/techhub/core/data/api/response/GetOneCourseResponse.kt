package com.jonareas.android.techhub.core.data.api.response

import com.jonareas.android.techhub.core.data.api.model.ApiInstructor
import com.jonareas.android.techhub.core.data.api.model.ApiTopic
import com.jonareas.android.techhub.core.data.cache.model.CachedCourse
import kotlinx.serialization.Serializable

@Serializable
data class GetOneCourseResponse(
    val id: Int,
    val name: String,
    val description: String,
    val imageUrl: String,
    val imageContentDesc: String,
    val step: Int,
    val steps: Int,
    val instructor: ApiInstructor,
    val topic: ApiTopic
) {

    fun toModel() : CachedCourse =
        CachedCourse(id, name, topic.name, imageUrl, imageContentDesc, description, steps, step,
        instructorName = instructor.fullName,
        instructorPhotoPath = instructor.photoPath, favorite = true)

}