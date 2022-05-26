package ru.maksonic.rdcompose.data.categories.cloud

import com.google.firebase.firestore.DocumentSnapshot
import javax.inject.Inject

/**
 * @Author maksonic on 26.05.2022
 */
typealias CloudMapper<T, R> = (T) -> R

class FirestoreDocumentToCloudMapper @Inject constructor(
) : CloudMapper<DocumentSnapshot, CategoryCloud> {

    override fun invoke(documentSnapshot: DocumentSnapshot): CategoryCloud {
        val doc = documentSnapshot.data
        val id = doc?.get("id").toString().toLong()
        val name = doc?.get("name").toString()
        val description = doc?.get("description").toString()
        val image = doc?.get("image").toString()

        return CategoryCloud(id, name, description, image)
    }
}
