package com.jonareas.android.techhub.core.data.cache.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jonareas.android.techhub.core.data.cache.dao.CourseDao
import com.jonareas.android.techhub.core.data.cache.dao.TopicDao
import com.jonareas.android.techhub.core.data.cache.database.TechHubDatabase.Companion.DATABASE_VERSION
import com.jonareas.android.techhub.core.data.cache.model.CachedCourse
import com.jonareas.android.techhub.core.data.cache.model.CachedTopic

@Database(entities = [CachedCourse::class, CachedTopic::class], version = DATABASE_VERSION, exportSchema = false)
abstract class TechHubDatabase : RoomDatabase() {

    abstract val topicDao : TopicDao

    abstract val courseDao: CourseDao

    companion object {
        internal const val DATABASE_VERSION : Int = 1
        internal const val DATABASE_NAME : String = "TechHub.db"
    }


}