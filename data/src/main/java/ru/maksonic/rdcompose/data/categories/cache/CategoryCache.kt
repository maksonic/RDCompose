package ru.maksonic.rdcompose.data.categories.cache

import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.maksonic.rdcompose.core.common.Abstract

/**
 * @Author maksonic on 26.05.2022
 */
@Entity(tableName = "categories")
data class CategoryCache(
    @PrimaryKey
    val id: Long? = null,
    val categoryId: String = "",
    val name: String = "",
    val description: String = "",
    val image: String? = "",
): Abstract.CacheObject