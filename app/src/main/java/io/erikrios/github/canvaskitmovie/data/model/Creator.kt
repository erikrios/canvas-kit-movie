package io.erikrios.github.canvaskitmovie.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Creator(
    val id: Long,
    val name: String,
    val profilePath: String?
) : Parcelable