package com.jonareas.android.techhub.core.data.repository

import com.jonareas.android.techhub.core.data.cache.model.CachedCourse
import kotlinx.coroutines.flow.Flow

interface CourseRepository : BaseRepository<CachedCourse, Int> {

    suspend fun getAllCoursesByNameFlow(courseName : String) : Flow<List<CachedCourse>>

    suspend fun getRelatedCoursesFlow(courseId : Int) : Flow<List<CachedCourse>>

    suspend fun fetchCourses() : Flow<List<CachedCourse>>

}