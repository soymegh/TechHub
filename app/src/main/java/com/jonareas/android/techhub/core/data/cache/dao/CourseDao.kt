package com.jonareas.android.techhub.core.data.cache.dao

import androidx.room.Dao
import androidx.room.Query
import com.jonareas.android.techhub.core.data.cache.model.CachedCourse

@Dao
interface CourseDao : BaseDao<CachedCourse> {

    @Query("SELECT * FROM courses")
    fun getAll() : List<CachedCourse>

    @Query("SELECT * FROM courses WHERE name LIKE '%' || :courseName || '%'")
    fun getAllCoursesByName(courseName : String) : List<CachedCourse>

    @Query("SELECT * FROM courses where courseId = :id")
    fun getById(id : Int) : CachedCourse

    @Query("SELECT * FROM courses WHERE name LIKE :name LIMIT :pageSize OFFSET :pageIndex")
    fun getRelatedCourses(name : String, pageSize : Int = 5, pageIndex : Int = 1) : List<CachedCourse>
}