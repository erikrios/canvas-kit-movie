package io.erikrios.github.canvaskitmovie.core.data.source.remote.response.trending

import com.google.gson.annotations.SerializedName

data class GenreResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String
)