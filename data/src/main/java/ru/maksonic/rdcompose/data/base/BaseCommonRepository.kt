package ru.maksonic.rdcompose.data.base

import kotlinx.coroutines.flow.transform
import ru.maksonic.rdcompose.core.common.Abstract
import ru.maksonic.rdcompose.core.common.Mapper
import ru.maksonic.rdcompose.domain.base.CommonRepository

/**
 * @Author maksonic on 29.05.2022
 */
abstract class BaseCommonRepository<
        A : Abstract.CloudObject,
        B : Abstract.CacheObject,
        C : Abstract.DataObject,
        D : Abstract.DomainObject>(
    private val baseCloudDataSource: BaseCloudDataSource.Base<A>,
    private val baseCacheDataSource: BaseCacheDataSource.Base<B>,
    private val cloudMapper: Mapper<A, C>,
    private val cacheMapper: Mapper<B, C>,
    private val dataToDomainMapper: Mapper<C, D>
) : CommonRepository<D>() {

    override fun fetchDataList(): DataList<D> =
        baseCacheDataSource.fetchCacheList().transform { cacheRequest ->
            cacheRequest.onSuccess { cacheList ->
                val dataList = cacheMapper.mapFromList(cacheList)
                val domainList = dataToDomainMapper.mapFromList(dataList)
                emit(Result.success(domainList))
            }
            cacheRequest.onFailure {
                fetchCloudDataList().collect { cloudRequest ->
                    cloudRequest.onSuccess { cloudLists ->
                        emit(Result.success(cloudLists))
                    }
                    cloudRequest.onFailure { throwable ->
                        emit(Result.failure(throwable))
                    }
                }
            }
        }

    override fun fetchCloudDataList(categoryId: String): DataList<D> =
        baseCloudDataSource.fetchCloudList(categoryId).transform { cloudRequest ->
            cloudRequest.onSuccess { cloudList ->
                val dataList = cloudMapper.mapFromList(cloudList)
                val cacheList = cacheMapper.mapToList(dataList)
                baseCacheDataSource.insertCacheList(cacheList)
                val domainList = dataToDomainMapper.mapFromList(dataList)
                emit(Result.success(domainList))
            }
            cloudRequest.onFailure { throwable ->
                emit(Result.failure(throwable))
            }
        }
}