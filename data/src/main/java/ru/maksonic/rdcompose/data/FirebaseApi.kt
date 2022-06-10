package ru.maksonic.rdcompose.data

import android.content.Context
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import javax.inject.Inject

/**
 * @Author maksonic on 26.05.2022
 */
interface FirebaseApi {
    val instance: FirebaseFirestore
    val categoriesCollection: CollectionReference
    val podcastsCollections: CollectionReference
    val podcastsCollection: String

    class Base @Inject constructor(): FirebaseApi {
        private companion object {
            private const val CATEGORIES = "categories"
            private const val PODCASTS = "podcast_list"
        }

        override val instance = Firebase.firestore
        override val categoriesCollection = instance.collection(CATEGORIES)
        override val podcastsCollection = PODCASTS
         override val podcastsCollections = instance.collection(PODCASTS)
    }
}