package io.erikrios.github.canvaskitmovie.database

import androidx.paging.DataSource
import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import androidx.sqlite.db.SupportSQLiteQuery
import io.erikrios.github.canvaskitmovie.data.model.Movie
import io.erikrios.github.canvaskitmovie.database.DatabaseContract.MovieColumns.Companion.COLUMN_ID
import io.erikrios.github.canvaskitmovie.database.DatabaseContract.MovieColumns.Companion.TABLE_NAME

@Dao
interface FavoriteMovieDao {

    @Insert(onConflict = REPLACE)
    suspend fun insert(movie: Movie): Long

    @RawQuery(observedEntities = [Movie::class])
    fun getMovies(query: SupportSQLiteQuery): DataSource.Factory<Int, Movie>

    @Query("SELECT * FROM $TABLE_NAME WHERE $COLUMN_ID = :id")
    suspend fun getMovie(id: Int): Movie?

    @Delete
    suspend fun deleteMovie(movie: Movie): Int
}