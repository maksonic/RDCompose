package ru.maksonic.rdcompose.data.base

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import ru.maksonic.rdcompose.core.common.Abstract
import ru.maksonic.rdcompose.core.di.IoDispatcher

/**
 * @Author maksonic on 23.05.2022
 */
interface BaseCloudDataSource<C : Abstract.CloudObject> {

    fun fetchCloudList(): DataList<C>

    abstract class Base<C : Abstract.CloudObject>(
        @IoDispatcher private val dispatcher: CoroutineDispatcher
    ) : BaseCloudDataSource<C> {
        private companion object {
            private const val FAKE_DELAY = 3000L
        }

        protected abstract fun cloudList(rawString: String): List<C>

        override fun fetchCloudList() = flow<Result<List<C>>> {

        }.flowOn(dispatcher)
    }
}