package com.jonareas.android.techhub.core.data.cache.repository

import com.jonareas.android.techhub.core.data.cache.model.CachedCourse
import kotlinx.coroutines.flow.Flow

interface CourseRepository : BaseRepository<CachedCourse, Int> {

    suspend fun getRelatedCoursesFlow(courseName : String) : Flow<List<CachedCourse>>
}