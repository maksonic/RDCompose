package ru.maksonic.rdcompose.data.categories.cloud

import kotlinx.coroutines.CoroutineDispatcher
import ru.maksonic.rdcompose.core.di.IoDispatcher
import ru.maksonic.rdcompose.data.base.BaseCloudDataSource
import ru.maksonic.rdcompose.data.base.exception.ExceptionHandler

/**
 * @Author maksonic on 13.06.2022
 */
class CategoriesCloudDataSource(
    firestoreMapper: FirestoreCategoryToCloudMapper,
    exceptionHandler: ExceptionHandler,
    @IoDispatcher dispatcher: CoroutineDispatcher
) : BaseCloudDataSource.Base<CategoryCloud>(firestoreMapper, exceptionHandler, dispatcher)