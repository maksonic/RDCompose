package ru.maksonic.rdcompose.data.podcasts.cloud

import android.util.Log
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.transform
import kotlinx.coroutines.withTimeout
import ru.maksonic.rdcompose.core.di.IoDispatcher
import ru.maksonic.rdcompose.data.FirebaseApi
import ru.maksonic.rdcompose.data.base.exception.EmptyCloudDataException
import ru.maksonic.rdcompose.data.base.exception.ExceptionHandler
import ru.maksonic.rdcompose.data.categories.cloud.CategoriesCloudDataSource
import javax.inject.Inject

/**
 * @Author maksonic on 13.06.2022
 */
typealias PodcastsCloud = Flow<Result<List<PodcastCloud>>>

interface PodcastsCloudDataSource {
    fun fetchAllPodcasts(): PodcastsCloud
    fun fetchPodcastsByCategoryId(categoryId: String): PodcastsCloud

    class Base @Inject constructor(
        private val firebaseApi: FirebaseApi,
        private val cloudDataSource: CategoriesCloudDataSource,
        private val firestoreMapper: FirestorePodcastToCloudMapper,
        private val exceptionHandler: ExceptionHandler,
        @IoDispatcher private val dispatcher: CoroutineDispatcher
    ) : PodcastsCloudDataSource {
        private companion object {
            private const val TIME_OUT = 15000L
        }

        override fun fetchAllPodcasts(): PodcastsCloud =
            cloudDataSource.fetchCloudCategories().transform { categoriesResponse ->
                categoriesResponse.onSuccess { categories ->
                    try {
                        val podcasts = mutableListOf<PodcastCloud>()
                        for (category in categories) {
                            val snapshot =
                                firebaseApi.getFirestorePodcastsByCategory(category.categoryId)
                            if (!snapshot.isEmpty) {
                                val podcastsByCategory = snapshot.map(firestoreMapper)
                                podcasts.addAll(podcastsByCategory)
                            } else {
                                emit(
                                    Result.failure(
                                        exceptionHandler.handle(EmptyCloudDataException())
                                    )
                                )
                            }
                            Log.e("ALL PODCASTS", "${podcasts.count()}")
                            emit(Result.success(podcasts))
                        }
                    } catch (e: Exception) {
                        emit(Result.failure(exceptionHandler.handle(e)))
                    }
                }
                categoriesResponse.onFailure { error -> emit(Result.failure(error)) }
            }

        override fun fetchPodcastsByCategoryId(categoryId: String): PodcastsCloud = flow {
            try {
                withTimeout(TIME_OUT) {
                    val snapshot = firebaseApi.getFirestorePodcastsByCategory(categoryId)
                    if (!snapshot.isEmpty) {
                        val podcasts = snapshot.map(firestoreMapper)
                        emit(Result.success(podcasts))
                    } else {
                        emit(Result.failure(exceptionHandler.handle(EmptyCloudDataException())))
                    }
                }
            } catch (e: Exception) {
                emit(Result.failure(exceptionHandler.handle(e)))
            }
        }.flowOn(dispatcher)
    }

}