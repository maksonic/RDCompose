package ru.maksonic.rdcompose.domain.base

import kotlinx.coroutines.flow.Flow
import ru.maksonic.rdcompose.core.common.Abstract
import ru.maksonic.rdcompose.domain.categories.CategoryDomain

/**
 * @Author maksonic on 23.05.2022
 */
typealias Categories = Flow<Result<List<CategoryDomain>>>

interface Repository<T: Abstract.DomainObject> {
   // fun fetchDataList(): Flow<Result<List<T>>>
    fun fetchCloudDataList(): Flow<Result<List<T>>>
  //  fun findItemById(itemId: Long): Flow<Result<T>>
}