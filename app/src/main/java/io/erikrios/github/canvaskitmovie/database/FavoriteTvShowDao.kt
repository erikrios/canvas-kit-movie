package io.erikrios.github.canvaskitmovie.database

import androidx.paging.DataSource
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteQuery
import io.erikrios.github.canvaskitmovie.data.model.TvShow
import io.erikrios.github.canvaskitmovie.database.DatabaseContract.TvShowColumns.Companion.COLUMN_ID
import io.erikrios.github.canvaskitmovie.database.DatabaseContract.TvShowColumns.Companion.TABLE_NAME

@Dao
interface FavoriteTvShowDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(tvShow: TvShow): Long

    @RawQuery(observedEntities = [TvShow::class])
    suspend fun getTvShows(query: SupportSQLiteQuery): DataSource.Factory<Int, TvShow>

    @Query("SELECT * FROM $TABLE_NAME WHERE $COLUMN_ID = :id")
    suspend fun getTvShow(id: Int): TvShow?

    @Delete
    suspend fun deleteTvShow(tvShow: TvShow): Int
}