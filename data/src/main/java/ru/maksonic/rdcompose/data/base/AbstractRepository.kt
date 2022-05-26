package ru.maksonic.rdcompose.data.base

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.transform
import ru.maksonic.rdcompose.core.common.Abstract
import ru.maksonic.rdcompose.core.common.Mapper
import ru.maksonic.rdcompose.data.base.source.BaseCacheDataSource
import ru.maksonic.rdcompose.data.base.source.BaseCloudDataSource
import ru.maksonic.rdcompose.domain.base.Repository

/**
 * @Author maksonic on 23.05.2022
 */
typealias DataList<T> = Flow<Result<List<T>>>

abstract class AbstractRepository<
        A : Abstract.CloudObject,
        B : Abstract.CacheObject,
        C : Abstract.DataObject,
        D : Abstract.DomainObject>(
    private val baseCloudDataSource: BaseCloudDataSource.Base<A>,
    private val baseCacheDataSource: BaseCacheDataSource.Base<B>?,
    private val cloudMapper: Mapper<A, C>,
    private val cacheMapper: Mapper<B, C>?,
    private val dataToDomainMapper: Mapper<C, D>
) : Repository<D> {

    override fun fetchCloudDataList(): Flow<Result<List<D>>> =
        baseCloudDataSource.fetchCloudList().transform { cloudRequest ->
            cloudRequest.onSuccess { cloudList ->
                val dataList = cloudMapper.mapFromList(cloudList)
                val domainList = dataToDomainMapper.mapFromList(dataList)
                emit(Result.success(domainList))
            }
            cloudRequest.onFailure { throwable ->
                emit(Result.failure(throwable))
            }
        }
}