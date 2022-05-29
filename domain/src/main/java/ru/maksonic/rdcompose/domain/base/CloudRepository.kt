package ru.maksonic.rdcompose.domain.base

import ru.maksonic.rdcompose.core.common.Abstract

/**
 * @Author maksonic on 29.05.2022
 */
abstract class CloudRepository<T: Abstract.DomainObject> : AbstractRepository<T>()
