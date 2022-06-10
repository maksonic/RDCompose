package ru.maksonic.rdcompose.data.categories.cloud

import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.Source
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.tasks.await
import ru.maksonic.rdcompose.core.common.ResourceProvider
import ru.maksonic.rdcompose.core.di.IoDispatcher
import ru.maksonic.rdcompose.core.store.KeyStore
import ru.maksonic.rdcompose.data.FirebaseApi
import ru.maksonic.rdcompose.data.base.BaseCloudDataSource
import ru.maksonic.rdcompose.data.base.exception.ExceptionHandler
import javax.inject.Inject

/**
 * @Author maksonic on 26.05.2022
 */
class CategoriesCloudDataSource @Inject constructor(
    private val firebaseApi: FirebaseApi,
    private val keyStore: KeyStore,
    cloudMapper: FirestoreCategoryToCloudMapper,
    rp: ResourceProvider,
    ex: ExceptionHandler,
    @IoDispatcher dispatcher: CoroutineDispatcher
) : BaseCloudDataSource.Base<CategoryCloud>(
    baseCloudMapper = cloudMapper,
    rp = rp,
    ex = ex,
    dispatcher = dispatcher
) {
    override suspend fun request(categoryId: String): QuerySnapshot =
        firebaseApi.categoriesCollection.orderBy(keyStore.fetchDataCategoryId).get(Source.DEFAULT)
            .await()
}