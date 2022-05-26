package ru.maksonic.rdcompose.data.base.exception

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
            val errorMsg = e.localizedMessage?.toString()
            return when {
                errorMsg.toString().contains("Timed out") -> {
                    TimeOutException(rp.getString(R.string.error_time_out))
                }
                e is EmptyCloudDataException -> {
                    EmptyCloudDataException(rp.getString(R.string.error_empty_cloud_list))
                }
                e is EmptyCacheException -> {
                    EmptyCacheException(rp.getString(R.string.error_empty_cache_list))
                }
                else -> e
            }
        }
    }
}