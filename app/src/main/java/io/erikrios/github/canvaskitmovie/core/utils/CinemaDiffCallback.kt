package io.erikrios.github.canvaskitmovie.core.utils

import androidx.recyclerview.widget.DiffUtil
import io.erikrios.github.canvaskitmovie.core.domain.model.Movie
import io.erikrios.github.canvaskitmovie.core.domain.model.TvShow

class CinemaDiffCallback<T> : DiffUtil.ItemCallback<T>() {
    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean =
        when (oldItem) {
            is Movie ->
                (oldItem as Movie).id == (newItem as Movie).id
            is TvShow -> (oldItem as TvShow).id == (newItem as TvShow).id
            else -> throw IllegalArgumentException("Only Movie or TvShow instance are accepted.")
        }

    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean =
        when (oldItem) {
            is Movie ->
                (oldItem as Movie) == (newItem as Movie)
            is TvShow -> (oldItem as TvShow) == (newItem as TvShow)
            else -> throw IllegalArgumentException("Only Movie or TvShow instance are accepted.")
        }
}