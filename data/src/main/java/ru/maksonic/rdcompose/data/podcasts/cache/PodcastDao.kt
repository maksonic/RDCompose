package ru.maksonic.rdcompose.data.podcasts.cache

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import ru.maksonic.rdcompose.data.base.BaseDao
import ru.maksonic.rdcompose.data.podcasts.PodcastData

/**
 * @Author maksonic on 11.06.2022
 */
@Dao
abstract class PodcastDao : BaseDao<PodcastCache> {

    @Query("SELECT * FROM podcasts")
    abstract override suspend fun fetchCacheList(): List<PodcastCache>

    @Query("SELECT * FROM podcasts WHERE categoryId = :categoryId")
    abstract suspend fun fetchCachePodcastsByCategoryId(categoryId: Long): List<PodcastCache>

    @Query("SELECT * FROM podcasts WHERE isNew = :isNew")
    abstract suspend fun fetchNewPodcasts(isNew: Boolean = true): List<PodcastCache>

    @Query("SELECT * FROM podcasts WHERE isRecommend = :isRecommend")
    abstract suspend fun fetchRecommendPodcasts(isRecommend: Boolean = true): List<PodcastCache>

    @Query("SELECT * FROM podcasts WHERE isTop = :isTop")
    abstract suspend fun fetchTopPodcasts(isTop: Boolean = true): List<PodcastCache>

    @Query("SELECT * FROM podcasts WHERE id = :itemId")
    abstract override fun fetchCacheItemById(itemId: Long): Flow<PodcastCache>

}