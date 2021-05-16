package io.github.erikrios.canvaskitmovie.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Creator(
    val id: Long,
    val name: String,
    val profilePath: String?
) : Parcelable