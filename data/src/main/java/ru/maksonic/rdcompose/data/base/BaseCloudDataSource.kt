package ru.maksonic.rdcompose.data.base

import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.QuerySnapshot
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withTimeout
import ru.maksonic.rdcompose.core.common.Abstract
import ru.maksonic.rdcompose.core.common.CloudMapper
import ru.maksonic.rdcompose.core.di.IoDispatcher
import ru.maksonic.rdcompose.data.base.exception.EmptyCloudDataException
import ru.maksonic.rdcompose.data.base.exception.ExceptionHandler
import ru.maksonic.rdcompose.domain.base.DataList

/**
 * @Author maksonic on 20.06.2022
 */

interface BaseCloudDataSource<T> {
    fun fetchCloudList(snapshot: suspend () -> QuerySnapshot): DataList<T>

    abstract class Base<T : Abstract.CloudObject>(
        private val cloudMapper: CloudMapper<DocumentSnapshot, T>,
        private val exceptionHandler: ExceptionHandler,
        @IoDispatcher private val dispatcher: CoroutineDispatcher
    ) : BaseCloudDataSource<T> {
        private companion object {
            private const val TIME_OUT = 15000L
        }

        override fun fetchCloudList(snapshot: suspend () -> QuerySnapshot): DataList<T> = flow {
            try {
                withTimeout(TIME_OUT) {
                    val firestoreList = snapshot().documents.toList()
                    val cloudList = firestoreList.map { cloudMapper(it) }
                    if (cloudList.isNotEmpty())
                        emit(Result.success(cloudList))
                    else
                        emit(Result.failure(exceptionHandler.handle(EmptyCloudDataException())))
                }
            } catch (e: Exception) {
                emit(Result.failure(exceptionHandler.handle(e)))
            }
        }.flowOn(dispatcher)
    }
}
