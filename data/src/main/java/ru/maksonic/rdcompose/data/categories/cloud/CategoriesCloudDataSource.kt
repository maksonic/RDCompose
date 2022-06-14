package ru.maksonic.rdcompose.data.categories.cloud

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withTimeout
import ru.maksonic.rdcompose.core.di.IoDispatcher
import ru.maksonic.rdcompose.data.FirebaseApi
import ru.maksonic.rdcompose.data.base.exception.EmptyCloudDataException
import ru.maksonic.rdcompose.data.base.exception.ExceptionHandler
import javax.inject.Inject

/**
 * @Author maksonic on 13.06.2022
 */
typealias CategoriesCloud = Flow<Result<List<CategoryCloud>>>

interface CategoriesCloudDataSource {
    fun fetchCloudCategories(): CategoriesCloud

    class Base @Inject constructor(
        private val firebaseApi: FirebaseApi,
        private val firestoreMapper: FirestoreCategoryToCloudMapper,
        private val exceptionHandler: ExceptionHandler,
        @IoDispatcher private val dispatcher: CoroutineDispatcher
    ) : CategoriesCloudDataSource {
        private companion object {
            private const val TIME_OUT = 15000L
        }

        override fun fetchCloudCategories(): CategoriesCloud = flow {
            try {
                withTimeout(TIME_OUT) {
                    val snapshot = firebaseApi.getAllFirestoreCategories()
                    if (!snapshot.isEmpty) {
                        val categories = snapshot.map(firestoreMapper)
                        emit(Result.success(categories))
                    } else {
                        throw EmptyCloudDataException()
                    }
                }
            } catch (e: Exception) {
                emit(Result.failure(exceptionHandler.handle(e)))
            }
        }.flowOn(dispatcher)
    }
}