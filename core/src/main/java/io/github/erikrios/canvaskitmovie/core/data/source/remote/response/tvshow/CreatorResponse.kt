package io.github.erikrios.canvaskitmovie.core.data.source.remote.response.tvshow

import com.google.gson.annotations.SerializedName

data class CreatorResponse(
    @SerializedName("id")
    val id: Long,
    @SerializedName("name")
    val name: String,
    @SerializedName("profile_path")
    val profilePath: String?
)