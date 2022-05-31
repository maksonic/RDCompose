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
                is TimeoutCancellationException -> {
                    TimeOutException(rp.getString(R.string.error_time_out))
                }
                is EmptyDataException -> {
                    EmptyDataException(rp.getString(R.string.error_empty_cloud_list))
                }
                is EmptyCloudDataException -> {
                    EmptyCloudDataException(rp.getString(R.string.error_empty_cloud_list))
                }
                is EmptyCacheException -> {
                    EmptyCacheException(rp.getString(R.string.error_empty_cache_list))
                }
                is FirebaseFirestoreException -> {
                    EmptyCloudDataException(rp.getString(R.string.error_empty_cloud_list))
                }
                else -> e
            }
        }
    }
}