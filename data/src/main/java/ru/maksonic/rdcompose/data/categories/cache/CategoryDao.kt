package ru.maksonic.rdcompose.data.categories.cache

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import ru.maksonic.rdcompose.data.base.BaseDao

/**
 * @Author maksonic on 27.05.2022
 */
@Dao
abstract class CategoryDao : BaseDao<CategoryCache> {

    @Query("SELECT * FROM categories")
    abstract override suspend fun fetchCacheList(): List<CategoryCache>

    @Query("SELECT * FROM categories WHERE id = :itemId")
    abstract override fun fetchCacheItemById(itemId: Long): Flow<CategoryCache>
}