package com.jonareas.android.techhub.core.data.repository.impl

import com.jonareas.android.techhub.core.data.cache.dao.TopicDao
import com.jonareas.android.techhub.core.data.cache.model.CachedTopic
import com.jonareas.android.techhub.core.data.repository.TopicRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class TopicRepositoryImpl @Inject constructor(private val dao: TopicDao) :
    BaseRepositoryImpl<CachedTopic, Int>(dao), TopicRepository {
    override suspend fun getAllOrderedByNameFlow(): Flow<List<CachedTopic>> = flow {
        emit(dao.getAllOrderedByName())
    }

    override suspend fun getAllFlow(): Flow<List<CachedTopic>> = flow {
        emit(dao.getAll())
    }

    override suspend fun getByIdFlow(id: Int): Flow<CachedTopic> = flow {
        emit(dao.getById(id))
    }

}