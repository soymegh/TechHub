package com.jonareas.android.techhub.core.data.cache.dao

import androidx.room.Dao
import androidx.room.Query
import com.jonareas.android.techhub.core.data.cache.model.CachedTopic


@Dao
interface TopicDao : BaseDao<CachedTopic> {

    @Query("SELECT * FROM topics")
    fun getAll() : List<CachedTopic>

    @Query("SELECT * FROM topics ORDER BY name")
    fun getAllOrderedByName() : List<CachedTopic>

    @Query("SELECT * FROM topics WHERE topicId = :id")
    fun getById(id : Int) : CachedTopic

}