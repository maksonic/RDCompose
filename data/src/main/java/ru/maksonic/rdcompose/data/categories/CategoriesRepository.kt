package ru.maksonic.rdcompose.data.categories

import ru.maksonic.rdcompose.data.base.AbstractRepository
import ru.maksonic.rdcompose.data.categories.cache.CategoryCache
import ru.maksonic.rdcompose.data.categories.cloud.CategoriesCloudDataSource
import ru.maksonic.rdcompose.data.categories.cloud.CategoryCloud
import ru.maksonic.rdcompose.data.categories.cloud.CategoryCloudToDataMapper
import ru.maksonic.rdcompose.domain.categories.CategoryDomain
import javax.inject.Inject

/**
 * @Author maksonic on 26.05.2022
 */
class CategoriesRepository @Inject constructor(
    cloudDataSource: CategoriesCloudDataSource,
    cloudMapper: CategoryCloudToDataMapper,
    dataToDomainMapper: CategoryDataToDomainMapper
): AbstractRepository<CategoryCloud, CategoryCache, CategoryData, CategoryDomain>(
    baseCloudDataSource = cloudDataSource,
    baseCacheDataSource = null,
    cloudMapper = cloudMapper,
    cacheMapper = null,
    dataToDomainMapper = dataToDomainMapper
)