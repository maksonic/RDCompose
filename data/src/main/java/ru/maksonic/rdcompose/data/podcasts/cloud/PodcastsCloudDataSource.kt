package ru.maksonic.rdcompose.data.podcasts.cloud

import com.google.firebase.firestore.QuerySnapshot
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.tasks.await
import ru.maksonic.rdcompose.core.common.ResourceProvider
import ru.maksonic.rdcompose.core.di.IoDispatcher
import ru.maksonic.rdcompose.data.FirebaseApi
import ru.maksonic.rdcompose.data.base.BaseCloudDataSource
import ru.maksonic.rdcompose.data.base.exception.ExceptionHandler
import javax.inject.Inject

/**
 * @Author maksonic on 29.05.2022
 */
class PodcastsCloudDataSource @Inject constructor(
    private val firebaseApi: FirebaseApi,
    cloudMapper: FirestorePodcastToCloudMapper,
    rp: ResourceProvider,
    ex: ExceptionHandler,
    @IoDispatcher dispatcher: CoroutineDispatcher
) : BaseCloudDataSource.Base<PodcastCloud>(
    baseCloudMapper = cloudMapper,
    rp = rp,
    ex = ex,
    dispatcher = dispatcher
) {
    override suspend fun request(categoryId: String): QuerySnapshot = with(firebaseApi) {
        categoriesCollection.document(categoryId).collection(podcastsCollection).get().await()
    }
}