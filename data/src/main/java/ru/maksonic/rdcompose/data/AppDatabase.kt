package ru.maksonic.rdcompose.data

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.maksonic.rdcompose.core.common.ResourceProvider
import ru.maksonic.rdcompose.data.categories.cache.CategoryCache
import ru.maksonic.rdcompose.data.categories.cache.CategoryDao
import ru.maksonic.rdcompose.data.podcasts.cache.PodcastCache
import ru.maksonic.rdcompose.data.podcasts.cache.PodcastDao

/**
 * @Author maksonic on 27.05.2022
 */
private const val DB_VERSION = 1

interface DatabaseName {
    val name: String

    class Base(rp: ResourceProvider) : DatabaseName {
        override val name = rp.getString(R.string.db_name)
    }
}

@Database(
    entities = [CategoryCache::class, PodcastCache::class],
    version = DB_VERSION,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun categoryDao(): CategoryDao
    abstract fun podcastDao(): PodcastDao
}