package ru.maksonic.rdcompose.data.categories.cloud

import com.google.firebase.firestore.DocumentSnapshot
import ru.maksonic.rdcompose.core.common.CloudMapper
import javax.inject.Inject

/**
 * @Author maksonic on 26.05.2022
 */
class FirestoreCategoryToCloudMapper @Inject constructor(
) : CloudMapper<DocumentSnapshot, CategoryCloud> {

    override fun invoke(documentSnapshot: DocumentSnapshot): CategoryCloud =
        documentSnapshot.toObject(CategoryCloud::class.java) ?: CategoryCloud()
}
