package ru.maksonic.rdcompose.data.categories.cloud

import ru.maksonic.rdcompose.core.common.Abstract

/**
 * @Author maksonic on 26.05.2022
 */
data class CategoryCloud(
    val id: Long,
    val name: String,
    val description: String,
    val image: String?,
): Abstract.CloudObject