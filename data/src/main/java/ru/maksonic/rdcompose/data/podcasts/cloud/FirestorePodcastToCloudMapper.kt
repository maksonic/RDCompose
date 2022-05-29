package ru.maksonic.rdcompose.data.podcasts.cloud

import com.google.firebase.firestore.DocumentSnapshot
import ru.maksonic.rdcompose.core.common.CloudMapper
import javax.inject.Inject

/**
 * @Author maksonic on 29.05.2022
 */
class FirestorePodcastToCloudMapper @Inject constructor(
) : CloudMapper<DocumentSnapshot, PodcastCloud> {

    override fun invoke(documentSnapshot: DocumentSnapshot): PodcastCloud =
        documentSnapshot.toObject(PodcastCloud::class.java) ?: PodcastCloud()
}
