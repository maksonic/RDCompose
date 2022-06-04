package ru.maksonic.rdcompose.data.base

import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.transform
import ru.maksonic.rdcompose.core.common.Abstract
import ru.maksonic.rdcompose.core.common.Mapper
import ru.maksonic.rdcompose.domain.base.CloudRepository

/**
 * @Author maksonic on 29.05.2022
 */
abstract class BaseCloudRepository<
        A : Abstract.CloudObject,
        C : Abstract.DataObject,
        D : Abstract.DomainObject>(
    private val baseCloudDataSource: BaseCloudDataSource.Base<A>,
    private val cloudMapper: Mapper<A, C>,
    private val dataToDomainMapper: Mapper<C, D>
) : CloudRepository<D>() {

    override fun fetchCloudDataList(categoryId: String): DataList<D> =
        baseCloudDataSource.fetchCloudList(categoryId).transform { cloudRequest ->
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