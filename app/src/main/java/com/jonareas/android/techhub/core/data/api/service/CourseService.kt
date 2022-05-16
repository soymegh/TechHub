package com.jonareas.android.techhub.core.data.api.service

import com.jonareas.android.techhub.core.data.api.response.GetOneCourseResponse
import com.jonareas.android.techhub.core.data.api.utils.ApiConstants
import retrofit2.http.GET

interface CourseService {

    @GET(ApiConstants.COURSES_END_POINT)
    suspend fun getAllCourses() : List<GetOneCourseResponse>
}