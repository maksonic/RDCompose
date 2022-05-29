package ru.maksonic.rdcompose.data.categories.cache

import kotlinx.coroutines.CoroutineDispatcher
import ru.maksonic.rdcompose.core.common.ResourceProvider
import ru.maksonic.rdcompose.core.di.IoDispatcher
import ru.maksonic.rdcompose.data.base.BaseCacheDataSource
import javax.inject.Inject

/**
 * @Author maksonic on 27.05.2022
 */
class CategoryCacheDataSource @Inject constructor(
    dao: CategoryDao,
    rp: ResourceProvider,
    @IoDispatcher dispatcher: CoroutineDispatcher
) : BaseCacheDataSource.Base<CategoryCache>(dao, rp, dispatcher)