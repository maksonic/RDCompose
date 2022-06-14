package ru.maksonic.rdcompose.data.podcasts.cache

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.maksonic.rdcompose.core.common.Abstract

/**
 * @Author maksonic on 11.06.2022
 */
@Entity(tableName = "podcasts")
data class PodcastCache(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    @ColumnInfo(name = "categoryId")
    val categoryId: String = "",
    val name: String = "",
    val image: String = "",
    val soundFile: String = "",
    @ColumnInfo(name = "isNew")
    val isNew: Boolean = false,
    @ColumnInfo(name = "isRecommend")
    val isRecommend: Boolean = false,
    @ColumnInfo(name = "isTop")
    val isTop: Boolean = false,
) : Abstract.CacheObject