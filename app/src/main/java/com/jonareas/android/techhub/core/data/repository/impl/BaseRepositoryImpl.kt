package com.jonareas.android.techhub.core.data.repository.impl

import com.jonareas.android.techhub.core.data.cache.dao.BaseDao
import com.jonareas.android.techhub.core.data.cache.model.CachedEntity
import com.jonareas.android.techhub.core.data.repository.BaseRepository
import java.io.Serializable

abstract class BaseRepositoryImpl<T : CachedEntity, ID : Serializable>(private val dao: BaseDao<T>) :
    BaseRepository<T, ID> {

    override suspend fun add(entity: T) = dao.insert(entity)

    override suspend fun addAll(vararg entity: T) = dao.insert(*entity)

    override suspend fun update(entity: T) = dao.update(entity)

    override suspend fun remove(entity: T) = dao.delete(entity)

    override suspend fun removeAll(vararg entity: T) = dao.delete(*entity)
}