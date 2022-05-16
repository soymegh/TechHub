package com.jonareas.android.techhub.core.data.api.response

import kotlinx.serialization.Serializable

@Serializable
class GetAllCoursesResponse(
    val courses : List<GetOneCourseResponse>
)