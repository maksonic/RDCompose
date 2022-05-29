package ru.maksonic.rdcompose.domain.base

import kotlinx.coroutines.flow.Flow
import ru.maksonic.rdcompose.core.common.Abstract

/**
 * @Author maksonic on 29.05.2022
 */
abstract class CommonRepository<T: Abstract.DomainObject> : AbstractRepository<T>() {
    abstract fun fetchDataList(): Flow<Result<List<T>>>
}