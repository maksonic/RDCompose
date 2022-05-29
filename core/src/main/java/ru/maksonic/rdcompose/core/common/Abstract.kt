package ru.maksonic.rdcompose.core.common

/**
 * @Author maksonic on 23.05.2022
 */
abstract class Abstract {
    interface CacheObject
    interface CloudObject
    interface DataObject
    interface DomainObject
    interface EmptyCacheObject: CacheObject
}