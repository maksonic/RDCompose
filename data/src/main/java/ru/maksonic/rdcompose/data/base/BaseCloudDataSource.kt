package ru.maksonic.rdcompose.data.base

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.QuerySnapshot
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.tasks.await
import ru.maksonic.rdcompose.core.common.Abstract
import ru.maksonic.rdcompose.core.common.ResourceProvider
import ru.maksonic.rdcompose.core.di.IoDispatcher
import ru.maksonic.rdcompose.data.FirebaseApi
import ru.maksonic.rdcompose.data.R
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
        @IoDispatcher private val dispatcher: CoroutineDispatcher
    ) : BaseCloudDataSource<C> {

        protected abstract val collection: CollectionReference
        protected abstract val orderByFiled: String

        override fun fetchCloudList() = flow {
            try {
                val snapshot = collection.orderBy(orderByFiled).get().await()

                if (snapshot.documents.isEmpty()) {
                    emit(
                        Result.failure(
                            EmptyCloudException(
                                rp.getString(R.string.error_empty_cloud_list)
                            )
                        )
                    )
                } else {
                    val firestoreList = snapshot.documents.toList().sortedBy { it.id }
                    val cloudList = firestoreList.map { baseCloudMapper.invoke(it) }
                    emit(Result.success(cloudList))
                }
            } catch (e: Exception) {
                emit(Result.failure(e))
            }

        }.flowOn(dispatcher)
    }
}