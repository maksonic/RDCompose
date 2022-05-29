package ru.maksonic.rdcompose.domain.base

import kotlinx.coroutines.flow.Flow
import ru.maksonic.rdcompose.core.common.Abstract
import ru.maksonic.rdcompose.domain.categories.CategoryDomain
import ru.maksonic.rdcompose.domain.podcasts.PodcastDomain

/**
 * @Author maksonic on 29.05.2022
 */
typealias Categories = Flow<Result<List<CategoryDomain>>>
typealias Podcasts = Flow<Result<List<PodcastDomain>>>

abstract class AbstractRepository<T: Abstract.DomainObject> {
    abstract fun fetchCloudDataList(categoryId: String = ""): Flow<Result<List<T>>>
}