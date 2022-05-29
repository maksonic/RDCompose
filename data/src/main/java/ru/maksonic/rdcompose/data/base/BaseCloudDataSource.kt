package ru.maksonic.rdcompose.data.base

import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.QuerySnapshot
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withTimeout
import ru.maksonic.rdcompose.core.common.Abstract
import ru.maksonic.rdcompose.core.common.CloudMapper
import ru.maksonic.rdcompose.core.common.ResourceProvider
import ru.maksonic.rdcompose.core.di.IoDispatcher
import ru.maksonic.rdcompose.data.base.exception.EmptyCloudDataException
import ru.maksonic.rdcompose.data.base.exception.ExceptionHandler

/**
 * @Author maksonic on 23.05.2022
 */
interface BaseCloudDataSource<C : Abstract.CloudObject> {
    fun fetchCloudList(categoryId: String = ""): DataList<C>

    abstract class Base<C : Abstract.CloudObject>(
        private val baseCloudMapper: CloudMapper<DocumentSnapshot, C>,
        private val rp: ResourceProvider,
        private val ex: ExceptionHandler,
        @IoDispatcher private val dispatcher: CoroutineDispatcher
    ) : BaseCloudDataSource<C> {
        private companion object {
            private const val TIME_OUT = 15000L
        }

        override fun fetchCloudList(categoryId: String) = flow {
            try {
                withTimeout(TIME_OUT) {
                    val firestoreList = request(categoryId).documents.toList()
                    val cloudList = firestoreList.map { baseCloudMapper.invoke(it) }
                    if (cloudList.isEmpty()) {
                        emit(Result.failure(ex.handle(EmptyCloudDataException())))
                    } else {
                        emit(Result.success(cloudList))
                    }
                }
            } catch (e: Exception) {
                emit(Result.failure(ex.handle(e)))
            }
        }.flowOn(dispatcher)

        protected abstract suspend fun request(categoryId: String = ""): QuerySnapshot
    }
}
