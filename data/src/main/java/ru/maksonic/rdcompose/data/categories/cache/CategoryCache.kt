package ru.maksonic.rdcompose.data.categories.cache

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.coroutines.flow.Flow
import ru.maksonic.rdcompose.core.common.Abstract

/**
 * @Author maksonic on 26.05.2022
 */
typealias CategoriesCache = Flow<Result<List<CategoryCache>>>
typealias CategoryCacheItem = Flow<Result<CategoryCache>>

@Entity(tableName = "categories")
data class CategoryCache(
    @PrimaryKey
    val id: Long = 0,
    val name: String = "",
    val description: String = "",
    val image: String = "",
): Abstract.CacheObject