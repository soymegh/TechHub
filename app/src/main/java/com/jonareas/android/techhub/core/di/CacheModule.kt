package com.jonareas.android.techhub.core.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.jonareas.android.techhub.core.data.cache.dao.CourseDao
import com.jonareas.android.techhub.core.data.cache.dao.TopicDao
import com.jonareas.android.techhub.core.data.cache.database.TechHubDatabase
import com.jonareas.android.techhub.core.data.cache.database.TechHubDatabase.Companion.DATABASE_NAME
import com.jonareas.android.techhub.core.data.provider.CourseProvider
import com.jonareas.android.techhub.core.data.provider.TopicProvider
import com.jonareas.android.techhub.utils.DispatcherProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Provider
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CacheModule {

    @Provides
    @Singleton
    fun provideCallback(
        dispatcherProvider: DispatcherProvider, topicDao: Provider<TopicDao>,
        courseDao : Provider<CourseDao>
    ) = object : RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            CoroutineScope(dispatcherProvider.io).launch {
                prepopulateDatabase(this, topicDao.get(), courseDao.get())
            }
        }
    }

    private suspend fun prepopulateDatabase(coroutineScope: CoroutineScope, topicDao: TopicDao, courseDao: CourseDao) {
        coroutineScope.launch {
            topicDao.insert(*TopicProvider.topics.toTypedArray())
        }
        coroutineScope.launch {
            courseDao.insert(*CourseProvider.courses.toTypedArray())
        }
    }

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context : Context, databaseCallback : RoomDatabase.Callback) =
        Room.databaseBuilder(context, TechHubDatabase::class.java, DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .addCallback(databaseCallback)
            .build()

    @Provides
    @Singleton
    fun provideTopicDao(database : TechHubDatabase) : TopicDao =
        database.topicDao

    @Provides
    @Singleton
    fun provideCourseDao(database : TechHubDatabase) : CourseDao =
        database.courseDao

}
