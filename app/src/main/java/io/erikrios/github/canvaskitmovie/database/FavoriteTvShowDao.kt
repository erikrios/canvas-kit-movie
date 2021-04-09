package io.erikrios.github.canvaskitmovie.database

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.erikrios.github.canvaskitmovie.data.model.TvShow
import io.erikrios.github.canvaskitmovie.database.DatabaseContract.TvShowColumns.Companion.COLUMN_ID
import io.erikrios.github.canvaskitmovie.database.DatabaseContract.TvShowColumns.Companion.TABLE_NAME

interface FavoriteTvShowDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(tvShow: TvShow): Long

    @Query("SELECT * FROM $TABLE_NAME")
    suspend fun getTvShows(): List<TvShow>

    @Query("SELECT * FROM $TABLE_NAME WHERE $COLUMN_ID = :id")
    suspend fun getTvShow(id: Int): TvShow

    @Delete
    suspend fun deleteTvShow(tvShow: TvShow): Int
}