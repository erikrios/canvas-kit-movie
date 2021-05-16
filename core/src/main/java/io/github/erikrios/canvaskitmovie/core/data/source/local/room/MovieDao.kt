package io.github.erikrios.canvaskitmovie.core.data.source.local.room

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import androidx.sqlite.db.SupportSQLiteQuery
import io.github.erikrios.canvaskitmovie.core.data.source.local.entity.MovieEntity
import io.github.erikrios.canvaskitmovie.core.data.source.local.room.DatabaseContract.MovieColumns.Companion.COLUMN_ID
import io.github.erikrios.canvaskitmovie.core.data.source.local.room.DatabaseContract.MovieColumns.Companion.TABLE_NAME
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Query("SELECT * FROM $TABLE_NAME")
    fun getMovies(): Flow<List<MovieEntity>>

    @Insert(onConflict = REPLACE)
    suspend fun insertMovies(movies: List<MovieEntity>)

    @Query("SELECT * FROM $TABLE_NAME WHERE $COLUMN_ID = :id")
    fun getMovie(id: Int): Flow<MovieEntity?>

    @RawQuery(observedEntities = [MovieEntity::class])
    fun getFavoriteMovies(query: SupportSQLiteQuery): Flow<List<MovieEntity>>

    @Update
    suspend fun updateMovie(movie: MovieEntity)

    @Update
    fun setFavoriteMovie(movie: MovieEntity)
}