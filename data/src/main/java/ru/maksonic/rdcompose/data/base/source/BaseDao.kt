package ru.maksonic.rdcompose.data.base.source

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import kotlinx.coroutines.flow.Flow
import ru.maksonic.rdcompose.core.common.Abstract

/**
 * @Author maksonic on 23.05.2022
 */
@Dao
interface BaseDao<T : Abstract.CacheObject> {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(data: List<T>): List<Long>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(item: T): Long

    suspend fun fetchCacheList(): List<@JvmSuppressWildcards T>

    fun fetchCacheItemById(itemId: Long): Flow<T>

    @Delete
    suspend fun deleteAllCachedList(data: List<T>)

    @Delete
    suspend fun deleteCachedItemById(itemId: Long)
}