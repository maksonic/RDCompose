package ru.maksonic.rdcompose.data.categories

import com.google.firebase.firestore.QuerySnapshot
import ru.maksonic.rdcompose.data.FirebaseApi
import ru.maksonic.rdcompose.data.base.BaseRepository
import ru.maksonic.rdcompose.data.categories.cache.CategoriesCacheDataSource
import ru.maksonic.rdcompose.data.categories.cache.CategoryCache
import ru.maksonic.rdcompose.data.categories.cache.CategoryCacheToDataMapper
import ru.maksonic.rdcompose.data.categories.cache.CategoryDao
import ru.maksonic.rdcompose.data.categories.cloud.CategoriesCloudDataSource
import ru.maksonic.rdcompose.data.categories.cloud.CategoryCloud
import ru.maksonic.rdcompose.data.categories.cloud.CategoryCloudToDataMapper
import ru.maksonic.rdcompose.domain.categories.CategoriesRepository
import ru.maksonic.rdcompose.domain.categories.CategoryDomain
import javax.inject.Inject

/**
 * @Author maksonic on 13.06.2022
 */
class CategoriesRepositoryImpl @Inject constructor(
    private val api: FirebaseApi,
    private val dao: CategoryDao,
    cloudSource: CategoriesCloudDataSource,
    cacheSource: CategoriesCacheDataSource.Base,
    cloudMapper: CategoryCloudToDataMapper,
    cacheMapper: CategoryCacheToDataMapper,
    domainMapper: CategoryDataToDomainMapper,
) : BaseRepository<CategoryCloud, CategoryCache, CategoryData, CategoryDomain>(
    cloudSource, cacheSource, cloudMapper, cacheMapper, domainMapper
), CategoriesRepository {
    override val snapshot: suspend () -> QuerySnapshot = { api.getCategories() }
    override suspend fun cacheList(): List<CategoryCache> = dao.fetchCacheList()
}