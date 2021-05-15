package io.erikrios.github.canvaskitmovie.core.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import io.erikrios.github.canvaskitmovie.core.data.source.local.room.DatabaseContract.TvShowColumns.Companion.COLUMN_ID
import io.erikrios.github.canvaskitmovie.core.data.source.local.room.DatabaseContract.TvShowColumns.Companion.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class TvShowEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = COLUMN_ID)
    val id: Int,
    val backdropPath: String? = null,
    val genres: List<GenreEntity>? = null,
    val originalLanguage: String,
    val originalName: String,
    val name: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String? = null,
    val firstAirDate: String,
    val status: String? = null,
    val voteAverage: Double,
    val voteCount: Int,
    val creators: List<CreatorEntity>? = null,
    var isFavorite: Boolean
)
