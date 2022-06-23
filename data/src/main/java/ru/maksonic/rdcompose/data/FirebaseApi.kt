package ru.maksonic.rdcompose.data

import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.Source
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await
import ru.maksonic.rdcompose.core.store.KeyStore
import javax.inject.Inject

/**
 * @Author maksonic on 26.05.2022
 */
interface FirebaseApi {
    suspend fun getCategories(): QuerySnapshot
    suspend fun getPodcasts(): QuerySnapshot
    suspend fun getPodcastsByCategory(categoryId: Long): QuerySnapshot
    suspend fun getNewPodcast(): QuerySnapshot
    suspend fun getRecommendPodcast(): QuerySnapshot
    suspend fun getTopPodcast(): QuerySnapshot

    class Base @Inject constructor(
        private val keyStore: KeyStore.DataKey
    ) : FirebaseApi {
        private companion object {
            private const val CATEGORIES = "categories"
            private const val PODCASTS = "podcasts"
        }

        private val instance = Firebase.firestore
        private val categoriesCollection = instance.collection(CATEGORIES)
        private val podcastsCollection = instance.collection(PODCASTS)

        override suspend fun getCategories(): QuerySnapshot =
            categoriesCollection.orderBy(keyStore.fetchDataCategoryId).get(Source.SERVER).await()

        override suspend fun getPodcasts(): QuerySnapshot =
            podcastsCollection.orderBy(keyStore.fetchDataPodcastId).get(Source.SERVER).await()

        override suspend fun getPodcastsByCategory(categoryId: Long): QuerySnapshot =
            podcastsCollection.whereEqualTo(keyStore.fetchDataPodcastId, categoryId)
                .get(Source.SERVER).await()

        override suspend fun getNewPodcast(): QuerySnapshot =
            podcastsCollection.orderBy(keyStore.fetchNewPodcasts).get(Source.SERVER).await()

        override suspend fun getRecommendPodcast(): QuerySnapshot =
            podcastsCollection.orderBy(keyStore.fetchRecommendPodcasts).get(Source.SERVER).await()

        override suspend fun getTopPodcast(): QuerySnapshot =
            podcastsCollection.orderBy(keyStore.fetchTopPodcasts).get(Source.SERVER).await()
    }
}