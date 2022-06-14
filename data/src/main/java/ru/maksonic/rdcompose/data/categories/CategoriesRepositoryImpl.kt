package ru.maksonic.rdcompose.data.categories

import kotlinx.coroutines.flow.transform
import ru.maksonic.rdcompose.data.categories.cache.CategoriesCacheDataSource
import ru.maksonic.rdcompose.data.categories.cache.CategoryCacheToDataMapper
import ru.maksonic.rdcompose.data.categories.cloud.CategoriesCloudDataSource
import ru.maksonic.rdcompose.data.categories.cloud.CategoryCloudToDataMapper
import ru.maksonic.rdcompose.domain.categories.Categories
import ru.maksonic.rdcompose.domain.categories.CategoriesRepository
import ru.maksonic.rdcompose.domain.categories.Category
import javax.inject.Inject

/**
 * @Author maksonic on 13.06.2022
 */
class CategoriesRepositoryImpl @Inject constructor(
    private val cloudSource: CategoriesCloudDataSource,
    private val cacheSource: CategoriesCacheDataSource,
    private val cloudMapper: CategoryCloudToDataMapper,
    private val cacheMapper: CategoryCacheToDataMapper,
    private val domainMapper: CategoryDataToDomainMapper,
) : CategoriesRepository {

    override fun fetchCategories(): Categories =
        cacheSource.readFromCache().transform { cacheResponse ->
            cacheResponse.onSuccess { cacheCategories ->
                val dataList = cacheMapper.mapFromList(cacheCategories)
                val categories = domainMapper.mapFromList(dataList)
                emit(Result.success(categories))
            }
            cacheResponse.onFailure {
                refreshCategories().collect { cloudRequest ->
                    cloudRequest.onSuccess { cloudLists ->
                        emit(Result.success(cloudLists))
                    }
                    cloudRequest.onFailure { throwable ->
                        emit(Result.failure(throwable))
                    }
                }
            }
        }

    override fun refreshCategories(): Categories =
        cloudSource.fetchCloudCategories().transform { cloudResponse ->
            cloudResponse.onSuccess { cloudCategories ->
                val dataList = cloudMapper.mapFromList(cloudCategories)
                val categories = domainMapper.mapFromList(dataList)
                cacheSource.saveToCache(dataList)
                emit(Result.success(categories))
            }
            cloudResponse.onFailure { error -> emit(Result.failure(error)) }
        }

    override suspend fun fetchCategoryById(id: Long): Category =
        cacheSource.readCategoryById(id).transform { findCategory ->
            findCategory.onSuccess { categoryCache ->
                val categoryData = cacheMapper.mapFrom(categoryCache)
                val category = domainMapper.mapFrom(categoryData)
                emit(Result.success(category))
            }
            findCategory.onFailure { error -> emit(Result.failure(error)) }
        }
}