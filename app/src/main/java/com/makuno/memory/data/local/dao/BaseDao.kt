package com.makuno.memory.data.local.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update

internal interface BaseDao<T> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg item: T)

    @Delete
    suspend fun delete(vararg item: T)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(vararg item: T)
}