package io.github.erikrios.canvaskitmovie.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import io.github.erikrios.canvaskitmovie.core.data.source.local.entity.MovieEntity
import io.github.erikrios.canvaskitmovie.core.data.source.local.entity.TrendingEntity
import io.github.erikrios.canvaskitmovie.core.data.source.local.entity.TvShowEntity

@Database(
    entities = [MovieEntity::class, TvShowEntity::class, TrendingEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class CinemaDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
    abstract fun tvShowDao(): TvShowDao
    abstract fun trendingDao(): TrendingDao
}