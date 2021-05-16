package io.github.erikrios.canvaskitmovie.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TvShow(
    val id: Int,
    val backdropPath: String? = null,
    val genres: List<Genre>? = null,
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
    val creators: List<Creator>? = null,
    var isFavorite: Boolean
) : Parcelable
