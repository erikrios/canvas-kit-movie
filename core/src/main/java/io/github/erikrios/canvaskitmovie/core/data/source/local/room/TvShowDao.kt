package io.github.erikrios.canvaskitmovie.core.data.source.local.room

import androidx.room.*
import androidx.sqlite.db.SupportSQLiteQuery
import io.github.erikrios.canvaskitmovie.core.data.source.local.entity.TvShowEntity
import io.github.erikrios.canvaskitmovie.core.data.source.local.room.DatabaseContract.TvShowColumns.Companion.COLUMN_ID
import io.github.erikrios.canvaskitmovie.core.data.source.local.room.DatabaseContract.TvShowColumns.Companion.TABLE_NAME
import kotlinx.coroutines.flow.Flow

@Dao
interface TvShowDao {

    @Query("SELECT * FROM $TABLE_NAME")
    fun getTvShows(): Flow<List<TvShowEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTvShows(tvShows: List<TvShowEntity>)

    @Query("SELECT * FROM $TABLE_NAME WHERE $COLUMN_ID = :id")
    fun getTvShow(id: Int): Flow<TvShowEntity?>

    @RawQuery(observedEntities = [TvShowEntity::class])
    fun getFavoriteTvShows(query: SupportSQLiteQuery): Flow<List<TvShowEntity>>

    @Update
    suspend fun updateTvShow(tvShow: TvShowEntity)

    @Update
    fun setFavoriteTvShow(tvShow: TvShowEntity)
}