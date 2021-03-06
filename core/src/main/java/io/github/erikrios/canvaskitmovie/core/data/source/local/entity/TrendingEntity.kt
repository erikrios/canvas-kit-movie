package io.github.erikrios.canvaskitmovie.core.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import io.github.erikrios.canvaskitmovie.core.data.source.local.room.DatabaseContract.TrendingColumns.Companion.COLUMN_ID
import io.github.erikrios.canvaskitmovie.core.data.source.local.room.DatabaseContract.TrendingColumns.Companion.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class TrendingEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = COLUMN_ID)
    val id: Int,
    val adult: Boolean,
    val backdropPath: String? = null,
    val budget: Int? = null,
    val genres: List<GenreEntity>? = null,
    val originalLanguage: String,
    val originalTitle: String,
    val title: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String? = null,
    val releaseDate: String,
    val revenue: Int? = null,
    val status: String? = null,
    val voteAverage: Double,
    val voteCount: Int,
    var isFavorite: Boolean = false
)
