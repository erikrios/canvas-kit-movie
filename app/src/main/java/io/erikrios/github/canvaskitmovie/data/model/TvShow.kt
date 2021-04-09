package io.erikrios.github.canvaskitmovie.data.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import io.erikrios.github.canvaskitmovie.database.DatabaseContract.TvShowColumns.Companion.COLUMN_ID
import io.erikrios.github.canvaskitmovie.database.DatabaseContract.TvShowColumns.Companion.TABLE_NAME
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = TABLE_NAME)
data class TvShow(
    @SerializedName("id")
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = COLUMN_ID)
    val id: Int,
    @SerializedName("backdrop_path")
    val backdropPath: String? = null,
    @SerializedName("genres")
    val genres: List<Genre>? = null,
    @SerializedName("original_language")
    val originalLanguage: String,
    @SerializedName("original_name")
    val originalName: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("overview")
    val overview: String,
    @SerializedName("popularity")
    val popularity: Double,
    @SerializedName("poster_path")
    val posterPath: String? = null,
    @SerializedName("first_air_date")
    val firstAirDate: String,
    @SerializedName("status")
    val status: String? = null,
    @SerializedName("vote_average")
    val voteAverage: Double,
    @SerializedName("vote_count")
    val voteCount: Int,
    @SerializedName("created_by")
    val creators: List<Creator>? = null
) : Parcelable
