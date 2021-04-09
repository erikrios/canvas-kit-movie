package io.erikrios.github.canvaskitmovie.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import io.erikrios.github.canvaskitmovie.data.model.Movie
import io.erikrios.github.canvaskitmovie.data.model.TvShow

@Database(
    entities = [Movie::class, TvShow::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class FavoriteCinemaDatabase : RoomDatabase() {

    abstract fun favoriteMovieDao(): FavoriteMovieDao
    abstract fun favoriteTvShowDao(): FavoriteTvShowDao
}