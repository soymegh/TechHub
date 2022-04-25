package com.jonareas.android.techhub.core.di

import android.content.Context
import android.content.res.Resources
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.jonareas.android.techhub.R
import com.jonareas.android.techhub.core.data.cache.dao.TopicDao
import com.jonareas.android.techhub.core.data.cache.database.TechHubDatabase
import com.jonareas.android.techhub.core.data.cache.database.TechHubDatabase.Companion.DATABASE_NAME
import com.jonareas.android.techhub.core.data.cache.model.CachedTopic
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
    fun provideCallback(dispatcherProvider: DispatcherProvider, resources: Resources, topicDao: Provider<TopicDao>) : RoomDatabase.Callback =
        object : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                CoroutineScope(dispatcherProvider.io).launch {
                    prepopulateDatabase(resources, topicDao.get())
                }
            }
        }


    private suspend fun prepopulateDatabase(resources: Resources, topicDao: TopicDao) {
        // Reading the players.json raw resource file into a String.
        val jsonString = resources.openRawResource(R.raw.topics).bufferedReader().use {
            it.readText()
        }
        // Converting it to a List using Gson.
        val typeToken = object : TypeToken<List<CachedTopic>>() {}.type
        val tennisPlayers = Gson().fromJson<List<CachedTopic>>(jsonString, typeToken).toTypedArray()
        // Inserting all players in the database
        topicDao.insert(*tennisPlayers)
    }


    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context, databaseCallback : RoomDatabase.Callback): TechHubDatabase =
        Room.databaseBuilder(context, TechHubDatabase::class.java, DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .addCallback(databaseCallback)
            .build()

    @Provides
    @Singleton
    fun provideTopicDao(database: TechHubDatabase): TopicDao = database.topicDao


}