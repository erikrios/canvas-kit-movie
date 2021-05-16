package io.erikrios.github.canvaskitmovie.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    val id: Int,
    val adult: Boolean,
    val backdropPath: String? = null,
    val budget: Int? = null,
    val genres: List<Genre>? = null,
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
    var isFavorite: Boolean
) : Parcelable
