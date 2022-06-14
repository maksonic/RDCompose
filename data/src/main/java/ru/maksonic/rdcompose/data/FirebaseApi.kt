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
    suspend fun getAllFirestoreCategories(): QuerySnapshot
    suspend fun getFirestorePodcastsByCategory(categoryId: String): QuerySnapshot
    suspend fun getFirestoreRecommendPodcasts(): QuerySnapshot

    class Base @Inject constructor(
        private val keyStore: KeyStore.DataKey
    ) : FirebaseApi {
        private companion object {
            private const val CATEGORIES = "categories"
            private const val PODCASTS = "podcast_list"
        }

        private val instance = Firebase.firestore
        private val categoriesCollection = instance.collection(CATEGORIES)

        override suspend fun getAllFirestoreCategories(): QuerySnapshot =
            categoriesCollection.orderBy(keyStore.fetchDataCategoryId)
                .get(Source.SERVER)
                .await()

        override suspend fun getFirestorePodcastsByCategory(categoryId: String): QuerySnapshot =
            categoriesCollection.document(categoryId).collection(PODCASTS).get(Source.SERVER)
                .await()

        override suspend fun getFirestoreRecommendPodcasts(): QuerySnapshot =
            categoriesCollection.document().collection(PODCASTS).document().collection("podcast_list").orderBy("isRecommend").get(Source.SERVER).await()

    }
}