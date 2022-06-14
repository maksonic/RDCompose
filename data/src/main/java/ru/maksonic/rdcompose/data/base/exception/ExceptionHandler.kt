package ru.maksonic.rdcompose.data.base.exception

import com.google.firebase.firestore.FirebaseFirestoreException
import kotlinx.coroutines.TimeoutCancellationException
import ru.maksonic.rdcompose.core.common.ResourceProvider
import ru.maksonic.rdcompose.data.R
import javax.inject.Inject

/**
 * @Author maksonic on 26.05.2022
 */
interface ExceptionHandler {
    fun handle(e: Exception): Exception

    class Base @Inject constructor(private val rp: ResourceProvider) : ExceptionHandler {
        override fun handle(e: Exception): Exception {
            return when (e) {

                is EmptyDataException -> {
                    EmptyDataException(rp.getString(R.string.error_empty_cloud_list))
                }
                is EmptyCacheDataException -> {
                    EmptyCacheDataException(rp.getString(R.string.error_empty_cache_list))
                }

                is CachedItemNotFound -> {
                    CachedItemNotFound(rp.getString(R.string.error_empty_cache_item))
                }

                is EmptyCloudDataException -> {
                    EmptyCloudDataException(rp.getString(R.string.error_empty_cloud_list))
                }
                is FirebaseFirestoreException -> {
                    EmptyCloudDataException(rp.getString(R.string.error_empty_cloud_list))
                }

                is TimeoutCancellationException -> {
                    TimeOutException(rp.getString(R.string.error_time_out))
                }
                else -> e
            }
        }
    }
}