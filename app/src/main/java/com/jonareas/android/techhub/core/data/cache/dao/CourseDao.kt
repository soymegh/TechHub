package com.jonareas.android.techhub.core.data.cache.dao

import androidx.room.Dao
import androidx.room.Query
import com.jonareas.android.techhub.core.data.cache.model.CachedCourse

@Dao
interface CourseDao : BaseDao<CachedCourse> {

    @Query("SELECT * FROM courses")
    fun getAll() : List<CachedCourse>

    @Query("SELECT * FROM courses WHERE favorite")
    fun getFavorites() : List<CachedCourse>

    @Query("UPDATE courses SET favorite = 1 WHERE courseId = :id")
    fun setAsFavorite(id : Int)

    @Query("DELETE FROM courses")
    fun deleteAll()

    @Query("SELECT * FROM courses WHERE name LIKE '%' || :courseName || '%'")
    fun getAllCoursesByName(courseName : String) : List<CachedCourse>

    @Query("SELECT * FROM courses where courseId = :id")
    fun getById(id : Int) : CachedCourse

    @Query("SELECT * FROM courses WHERE courseId <> :id ORDER BY instructorPhotoPath LIKE (SELECT instructorPhotoPath FROM courses WHERE courseId = :id) DESC, subject =  (SELECT subject FROM courses WHERE courseId = :id) DESC")
    fun getRelatedCourses(id : Int) : List<CachedCourse>
}