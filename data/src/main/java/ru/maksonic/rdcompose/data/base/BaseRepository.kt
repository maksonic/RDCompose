package ru.maksonic.rdcompose.data.base

import com.google.firebase.firestore.QuerySnapshot
import kotlinx.coroutines.flow.transform
import ru.maksonic.rdcompose.core.common.Abstract
import ru.maksonic.rdcompose.core.common.Mapper
import ru.maksonic.rdcompose.domain.base.DataItem
import ru.maksonic.rdcompose.domain.base.DataList
import ru.maksonic.rdcompose.domain.base.Repository

/**
 * @Author maksonic on 22.06.2022
 */
abstract class BaseRepository<
        Cloud : Abstract.CloudObject,
        Cache : Abstract.CacheObject,
        Data : Abstract.DataObject,
        Domain : Abstract.DomainObject>(
    private val cloudSource: BaseCloudDataSource<Cloud>,
    private val cacheSource: BaseCacheDataSource<Cache>,
    private val cloudMapper: Mapper<Cloud, Data>,
    private val cacheMapper: Mapper<Cache, Data>,
    private val domainMapper: Mapper<Data, Domain>
) : Repository<Domain> {

    protected abstract val snapshot: suspend () -> QuerySnapshot
    protected abstract suspend fun cacheList(): List<Cache>

    override suspend fun fetchData(id: Long?): DataList<Domain> =
        cacheSource.readCacheList(cacheList()).transform { cacheResponse ->
            cacheResponse.onSuccess { cacheList ->
                val podcasts = domainMapper.mapFromList(cacheMapper.mapFromList(cacheList))
                emit(Result.success(podcasts))
            }
            cacheResponse.onFailure {
                refreshData(id).collect { cloudResponse ->
                    cloudResponse.onSuccess { domainList ->
                        emit(Result.success(domainList))
                    }
                    cloudResponse.onFailure { error -> emit(Result.failure(error)) }
                }
            }
        }

    override suspend fun refreshData(id: Long?): DataList<Domain> =
        cloudSource.fetchCloudList(snapshot).transform { cloudResponse ->
            cloudResponse.onSuccess { cloudList ->
                val dataList = cloudMapper.mapFromList(cloudList)
                val cacheList = cacheMapper.mapToList(dataList)
                cacheSource.saveListToCache(cacheList)
                val podcasts = domainMapper.mapFromList(dataList)
                emit(Result.success(podcasts))
            }
            cloudResponse.onFailure { error -> emit(Result.failure(error)) }
        }

    override suspend fun fetchItemById(id: Long): DataItem<Domain> =
        cacheSource.readCacheItemById(id).transform { findCategory ->
            findCategory.onSuccess { categoryCache ->
                val categoryData = cacheMapper.mapFrom(categoryCache)
                val category = domainMapper.mapFrom(categoryData)
                emit(Result.success(category))
            }
            findCategory.onFailure { error -> emit(Result.failure(error)) }
        }

    protected fun fetchCombinedData(
        cacheDataSource: DataList<Cache>, cloudDataSource: DataList<Cloud>
    ): DataList<Domain> = cacheDataSource.transform { cacheResponse ->
        cacheResponse.onSuccess { cacheList ->
            val podcasts = domainMapper.mapFromList(cacheMapper.mapFromList(cacheList))
            emit(Result.success(podcasts))
        }
        cacheResponse.onFailure {
            cloudDataSource.collect { cloudResponse ->
                cloudResponse.onSuccess { cloudList ->
                    val dataList = cloudMapper.mapFromList(cloudList)
                    val cacheList = cacheMapper.mapToList(dataList)
                    cacheSource.saveListToCache(cacheList)
                    val podcasts = domainMapper.mapFromList(dataList)
                    emit(Result.success(podcasts))
                }
                cloudResponse.onFailure { error -> emit(Result.failure(error)) }
            }
        }
    }

    protected fun refreshCombinedData(
        cloudDataSource: DataList<Cloud>
    ): DataList<Domain> = cloudDataSource.transform { cloudResponse ->
        cloudResponse.onSuccess { cloudList ->
            val dataList = cloudMapper.mapFromList(cloudList)
            val cacheList = cacheMapper.mapToList(dataList)
            cacheSource.saveListToCache(cacheList)
            val podcasts = domainMapper.mapFromList(dataList)
            emit(Result.success(podcasts))
        }
        cloudResponse.onFailure { error -> emit(Result.failure(error)) }
    }
}
/*
abstract class AbstractRepository<
        Cloud : Abstract.CloudObject,
        Cache : Abstract.CacheObject,
        Data : Abstract.DataObject,
        Domain : Abstract.DomainObject,
        Args : Any?>(
    private val cacheSource: BaseCacheDataSource<Cache>,
    private val cloudMapper: Mapper<Cloud, Data>,
    private val cacheMapper: Mapper<Cache, Data>,
    private val domainMapper: Mapper<Data, Domain>,
): BaseRepository<Domain, DataItem<Domain>> {

    protected fun fetchData(
        cacheDataSource: DataList<Cache>, cloudDataSource: DataList<Cloud>
    ): DataList<Domain> = cacheDataSource.transform { cacheResponse ->
        cacheResponse.onSuccess { cacheList ->
            val podcasts = domainMapper.mapFromList(cacheMapper.mapFromList(cacheList))
            emit(Result.success(podcasts))
        }
        cacheResponse.onFailure {
            cloudDataSource.collect { cloudResponse ->
                cloudResponse.onSuccess { cloudList ->
                    val dataList = cloudMapper.mapFromList(cloudList)
                    val cacheList = cacheMapper.mapToList(dataList)
                    cacheSource.saveListToCache(cacheList)
                    val podcasts = domainMapper.mapFromList(dataList)
                    emit(Result.success(podcasts))
                }
                cloudResponse.onFailure { error -> emit(Result.failure(error)) }
            }
        }
    }

    protected fun refreshData(
        cloudDataSource: DataList<Cloud>
    ): DataList<Domain> = cloudDataSource.transform { cloudResponse ->
        cloudResponse.onSuccess { cloudList ->
            val dataList = cloudMapper.mapFromList(cloudList)
            val cacheList = cacheMapper.mapToList(dataList)
            cacheSource.saveListToCache(cacheList)
            val podcasts = domainMapper.mapFromList(dataList)
            emit(Result.success(podcasts))
        }
        cloudResponse.onFailure { error -> emit(Result.failure(error)) }
    }

    override suspend fun fetchItemById(id: Long): DataItem<Domain> =
        cacheSource.readCacheItemById(id).transform { findCategory ->
            findCategory.onSuccess { categoryCache ->
                val categoryData = cacheMapper.mapFrom(categoryCache)
                val category = domainMapper.mapFrom(categoryData)
                emit(Result.success(category))
            }
            findCategory.onFailure { error -> emit(Result.failure(error)) }
        }
}
*/
