package com.jonareas.android.techhub.core.data.cache.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Update
import com.jonareas.android.techhub.core.data.cache.model.CachedEntity

interface BaseDao<T : CachedEntity> {

    @Insert(onConflict = REPLACE)
    suspend fun insert(entity : T)

    @Insert(onConflict = REPLACE)
    suspend fun insert(vararg entity : T)

    @Update(onConflict = REPLACE)
    suspend fun update(entity : T)

    @Delete
    suspend fun delete(entity : T)

    @Delete
    suspend fun delete(vararg entity : T)

}