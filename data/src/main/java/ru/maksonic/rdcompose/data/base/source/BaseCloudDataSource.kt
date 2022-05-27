package ru.maksonic.rdcompose.data.base.source

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.Source
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withTimeout
import ru.maksonic.rdcompose.core.common.Abstract
import ru.maksonic.rdcompose.core.common.ResourceProvider
import ru.maksonic.rdcompose.core.di.IoDispatcher
import ru.maksonic.rdcompose.data.FirebaseApi
import ru.maksonic.rdcompose.data.base.DataList
import ru.maksonic.rdcompose.data.base.exception.EmptyCloudDataException
import ru.maksonic.rdcompose.data.base.exception.ExceptionHandler
import ru.maksonic.rdcompose.data.categories.cloud.CloudMapper

/**
 * @Author maksonic on 23.05.2022
 */
interface BaseCloudDataSource<C : Abstract.CloudObject> {
    fun fetchCloudList(): DataList<C>

    abstract class Base<C : Abstract.CloudObject>(
        private val firebaseApi: FirebaseApi,
        private val baseCloudMapper: CloudMapper<DocumentSnapshot, C>,
        private val rp: ResourceProvider,
        private val ex: ExceptionHandler,
        @IoDispatcher private val dispatcher: CoroutineDispatcher
    ) : BaseCloudDataSource<C> {
        private companion object {
            private const val TIME_OUT = 15000L
        }

        protected abstract val collection: CollectionReference
        protected abstract val orderByFiled: String

        override fun fetchCloudList() = flow {
            try {
                withTimeout(TIME_OUT) {
                    val snapshot = collection.orderBy(orderByFiled).get(Source.SERVER).await()
                    val firestoreList = snapshot.documents.toList()
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
    }
}
