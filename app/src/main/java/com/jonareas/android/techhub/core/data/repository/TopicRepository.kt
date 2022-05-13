package com.jonareas.android.techhub.core.data.repository

import com.jonareas.android.techhub.core.data.cache.model.CachedTopic
import kotlinx.coroutines.flow.Flow

interface TopicRepository : BaseRepository<CachedTopic, Int> {

    suspend fun getAllOrderedByNameFlow() : Flow<List<CachedTopic>>

}