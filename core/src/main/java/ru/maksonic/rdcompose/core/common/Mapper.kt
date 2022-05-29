package ru.maksonic.rdcompose.core.common

/**
 * @Author maksonic on 23.05.2022
 */
interface Mapper<I, O> {

    fun mapFrom(i: I): O

    fun mapTo(o: O): I

    fun mapFromList(list: List<I>): List<O> {
        return list.mapNotNull { mapFrom(it) }
    }

    fun mapToList(list: List<O>): List<I> {
        return list.mapNotNull { mapTo(it) }
    }
}

typealias CloudMapper<T, R> = (T) -> R