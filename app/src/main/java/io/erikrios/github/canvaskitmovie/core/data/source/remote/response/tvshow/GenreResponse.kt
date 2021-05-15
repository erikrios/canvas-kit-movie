package io.erikrios.github.canvaskitmovie.core.data.source.remote.response.tvshow

import com.google.gson.annotations.SerializedName

data class GenreResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String
)
