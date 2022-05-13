package com.jonareas.android.techhub.core.data.repository

import com.jonareas.android.techhub.core.data.cache.model.CachedEntity
import kotlinx.coroutines.flow.Flow

interface BaseRepository<T : CachedEntity, ID> {

    suspend fun getAllFlow() : Flow<List<T>>

    suspend fun getByIdFlow(id : ID) : Flow<T>

    suspend fun add(entity : T)

    suspend fun addAll(vararg entity : T)

    suspend fun update(entity : T)

    suspend fun remove(entity : T)

    suspend fun removeAll(vararg entity : T)

}