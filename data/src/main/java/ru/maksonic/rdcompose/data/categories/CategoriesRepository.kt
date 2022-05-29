package ru.maksonic.rdcompose.data.categories

import ru.maksonic.rdcompose.data.base.BaseCommonRepository
import ru.maksonic.rdcompose.data.categories.cache.CategoryCache
import ru.maksonic.rdcompose.data.categories.cache.CategoryCacheDataSource
import ru.maksonic.rdcompose.data.categories.cache.CategoryCacheToDataMapper
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
    cacheDataSource: CategoryCacheDataSource,
    cloudMapper: CategoryCloudToDataMapper,
    cacheMapper: CategoryCacheToDataMapper,
    dataToDomainMapper: CategoryDataToDomainMapper
) : BaseCommonRepository<CategoryCloud, CategoryCache, CategoryData, CategoryDomain>(
    baseCloudDataSource = cloudDataSource,
    baseCacheDataSource = cacheDataSource,
    cloudMapper = cloudMapper,
    cacheMapper = cacheMapper,
    dataToDomainMapper = dataToDomainMapper
)