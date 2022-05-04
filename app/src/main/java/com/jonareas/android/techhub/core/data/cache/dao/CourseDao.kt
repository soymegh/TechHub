package com.jonareas.android.techhub.core.data.cache.dao

import androidx.room.Dao
import androidx.room.Query
import com.jonareas.android.techhub.core.data.cache.model.CachedCourse

@Dao
interface CourseDao : BaseDao<CachedCourse> {

    @Query("SELECT * FROM courses")
    fun getAll() : List<CachedCourse>


    @Query("SELECT * FROM courses where courseId = :id")
    fun getById(id : Int) : CachedCourse

}