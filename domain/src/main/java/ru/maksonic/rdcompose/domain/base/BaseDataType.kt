package ru.maksonic.rdcompose.domain.base

import kotlinx.coroutines.flow.Flow

/**
 * @Author maksonic on 29.05.2022
 */
typealias DataList<T> = Flow<Result<List<T>>>
typealias DataItem<T> = Flow<Result<T>>
