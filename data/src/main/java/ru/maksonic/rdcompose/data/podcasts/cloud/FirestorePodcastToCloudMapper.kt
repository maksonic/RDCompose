package ru.maksonic.rdcompose.data.podcasts.cloud

import com.google.firebase.firestore.DocumentSnapshot
import ru.maksonic.rdcompose.core.common.CloudMapper
import javax.inject.Inject

/**
 * @Author maksonic on 29.05.2022
 */
class FirestorePodcastToCloudMapper @Inject constructor() :
    CloudMapper<DocumentSnapshot, PodcastCloud> {
    override fun invoke(documentSnapshot: DocumentSnapshot): PodcastCloud {
        val doc = documentSnapshot.data
        val id = doc?.get("id").toString().toLong()
        val name = doc?.get("name").toString()
        val categoryId = doc?.get("categoryId").toString()
        val image = doc?.get("image").toString()
        val soundFile = doc?.get("soundfile").toString()
        val isNew = doc?.get("isNew").toString().toBoolean()
        val isRecommend = doc?.get("isRecommend").toString().toBoolean()
        val isTop = doc?.get("isTop").toString().toBoolean()

        return PodcastCloud(id, categoryId, name, image, soundFile, isNew, isRecommend, isTop)
    }
}