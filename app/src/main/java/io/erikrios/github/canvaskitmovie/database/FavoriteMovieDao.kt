package io.erikrios.github.canvaskitmovie.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import io.erikrios.github.canvaskitmovie.data.model.Movie
import io.erikrios.github.canvaskitmovie.database.DatabaseContract.MovieColumns.Companion.COLUMN_ID
import io.erikrios.github.canvaskitmovie.database.DatabaseContract.MovieColumns.Companion.TABLE_NAME

@Dao
interface FavoriteMovieDao {

    @Insert(onConflict = REPLACE)
    suspend fun insert(movie: Movie): Long

    @Query("SELECT * FROM $TABLE_NAME")
    suspend fun getMovies(): List<Movie>

    @Query("SELECT * FROM $TABLE_NAME WHERE $COLUMN_ID = :id")
    suspend fun getMovie(id: Int): Movie?

    @Delete
    suspend fun deleteMovie(movie: Movie): Int
}