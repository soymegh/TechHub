package com.jonareas.android.techhub.core.data.repository.impl

import com.jonareas.android.techhub.core.data.api.service.CourseService
import com.jonareas.android.techhub.core.data.cache.dao.CourseDao
import com.jonareas.android.techhub.core.data.cache.model.CachedCourse
import com.jonareas.android.techhub.core.data.repository.CourseRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CourseRepositoryImpl @Inject constructor(private val dao : CourseDao, private val service : CourseService) :
    BaseRepositoryImpl<CachedCourse, Int>(dao), CourseRepository {

    override suspend fun getAllCoursesByNameFlow(courseName: String): Flow<List<CachedCourse>> = flow {
        emit(dao.getAllCoursesByName(courseName))
    }

    override suspend fun getRelatedCoursesFlow(): Flow<List<CachedCourse>> = flow {
        emit(dao.getRelatedCourses())
    }

    override suspend fun fetchCourses(): Flow<List<CachedCourse>> = flow {
        val courses = service.getAllCourses().map { it.toModel() }
        dao.delete()
        dao.insert(*courses.toTypedArray())
        emit(dao.getAll())
    }

    override suspend fun getAllFlow(): Flow<List<CachedCourse>> = flow {
        emit(dao.getAll())
    }

    override suspend fun getByIdFlow(id: Int): Flow<CachedCourse> = flow {
        emit(dao.getById(id))
    }

}