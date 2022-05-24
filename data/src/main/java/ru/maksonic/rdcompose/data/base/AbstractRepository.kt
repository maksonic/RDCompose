package ru.maksonic.rdcompose.data.base

import ru.maksonic.rdcompose.core.common.Abstract
import ru.maksonic.rdcompose.core.common.Mapper
import ru.maksonic.rdcompose.domain.base.Repository

/**
 * @Author maksonic on 23.05.2022
 */
abstract class AbstractRepository<
        A : Abstract.CloudObject,
        B : Abstract.CacheObject,
        C : Abstract.DataObject,
        D : Abstract.DomainObject>(
    private val baseCloudDataSource: BaseCloudDataSource.Base<A>,
    private val baseCacheDataSource: BaseCacheDataSource.Base<B>,
    private val cacheMapper: Mapper<B, C>,
    private val cloudMapper: Mapper<A, C>,
    private val dataToDomainMapper: Mapper<C, D>
) : Repository<D> {

}