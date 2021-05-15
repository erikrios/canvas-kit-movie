package io.erikrios.github.canvaskitmovie.core.data.source.local.room

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import androidx.sqlite.db.SupportSQLiteQuery
import io.erikrios.github.canvaskitmovie.core.data.source.local.entity.TrendingEntity
import io.erikrios.github.canvaskitmovie.core.data.source.local.room.DatabaseContract.TrendingColumns.Companion.COLUMN_ID
import io.erikrios.github.canvaskitmovie.core.data.source.local.room.DatabaseContract.TrendingColumns.Companion.TABLE_NAME
import kotlinx.coroutines.flow.Flow

@Dao
interface TrendingDao {

    @Query("SELECT * FROM $TABLE_NAME")
    fun getTrendings(): Flow<List<TrendingEntity>>

    @Insert(onConflict = REPLACE)
    suspend fun insertTrendings(trendings: List<TrendingEntity>)

    @Query("SELECT * FROM $TABLE_NAME WHERE $COLUMN_ID = :id")
    fun getTrending(id: Int): Flow<TrendingEntity?>

    @RawQuery(observedEntities = [TrendingEntity::class])
    fun getFavoriteTrendings(query: SupportSQLiteQuery): Flow<List<TrendingEntity>>

    @Update
    fun updateTrending(trending: TrendingEntity)
}