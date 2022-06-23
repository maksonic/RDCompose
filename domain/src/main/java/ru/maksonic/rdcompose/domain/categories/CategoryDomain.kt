package ru.maksonic.rdcompose.domain.categories

import kotlinx.coroutines.flow.Flow
import ru.maksonic.rdcompose.core.common.Abstract

/**
 * @Author maksonic on 26.05.2022
 */
data class CategoryDomain(
    val id: Long = 0,
    val name: String = "",
    val description: String = "",
    val image: String = "",
): Abstract.DomainObject

typealias Categories = Flow<Result<List<CategoryDomain>>>
typealias Category = Flow<Result<CategoryDomain>>