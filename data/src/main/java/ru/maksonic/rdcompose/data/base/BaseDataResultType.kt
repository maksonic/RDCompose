package ru.maksonic.rdcompose.data.base

import kotlinx.coroutines.flow.Flow

/**
 * @Author maksonic on 29.05.2022
 */
typealias DataList<T> = Flow<Result<List<T>>>
