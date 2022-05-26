package ru.maksonic.rdcompose.data.categories.cloud

import com.google.firebase.firestore.CollectionReference
import kotlinx.coroutines.CoroutineDispatcher
import ru.maksonic.rdcompose.core.common.ResourceProvider
import ru.maksonic.rdcompose.core.di.IoDispatcher
import ru.maksonic.rdcompose.data.FirebaseApi
import ru.maksonic.rdcompose.data.base.source.BaseCloudDataSource
import ru.maksonic.rdcompose.data.base.exception.ExceptionHandler
import javax.inject.Inject

/**
 * @Author maksonic on 26.05.2022
 */
class CategoriesCloudDataSource @Inject constructor(
    firebaseApi: FirebaseApi,
    cloudMapper: FirestoreDocumentToCloudMapper,
    rp: ResourceProvider,
    ex: ExceptionHandler,
    @IoDispatcher dispatcher: CoroutineDispatcher
) : BaseCloudDataSource.Base<CategoryCloud>(
    firebaseApi = firebaseApi,
    baseCloudMapper = cloudMapper,
    rp = rp,
    ex = ex,
    dispatcher = dispatcher
) {
    private companion object {
        private const val ID = "id"
    }

    override val collection: CollectionReference = firebaseApi.categoriesCollection
    override val orderByFiled: String = ID
}