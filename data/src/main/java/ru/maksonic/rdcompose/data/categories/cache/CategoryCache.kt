package ru.maksonic.rdcompose.data.categories.cache

import ru.maksonic.rdcompose.core.common.Abstract

/**
 * @Author maksonic on 26.05.2022
 */
data class CategoryCache(
    val id: Long,
    val name: String,
    val description: String,
    val image: String?,
): Abstract.CacheObject