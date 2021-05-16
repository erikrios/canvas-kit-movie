package io.erikrios.github.canvaskitmovie.core.utils

import androidx.recyclerview.widget.DiffUtil
import io.erikrios.github.canvaskitmovie.core.domain.model.Movie
import io.erikrios.github.canvaskitmovie.core.domain.model.Trending
import io.erikrios.github.canvaskitmovie.core.domain.model.TvShow

class CinemaDiffCallback<T>(
    private val oldCinemas: List<T>,
    private val newCinemas: List<T>
) : DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldCinema = oldCinemas[oldItemPosition]
        val newCinema = newCinemas[newItemPosition]

        return when (oldCinema) {
            is Movie ->
                (oldCinema as Movie).id == (newCinema as Movie).id
            is TvShow -> (oldCinema as TvShow).id == (newCinema as TvShow).id
            is Trending -> (oldCinema as Trending).id == (newCinema as Trending).id
            else -> throw IllegalArgumentException("Only Movie, TvShow, or Trending instance are accepted.")
        }
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldCinemas[oldItemPosition] == newCinemas[newItemPosition]

    override fun getOldListSize(): Int = oldCinemas.size

    override fun getNewListSize(): Int = newCinemas.size
}