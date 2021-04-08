package io.erikrios.github.canvaskitmovie.utils

import androidx.recyclerview.widget.DiffUtil
import io.erikrios.github.canvaskitmovie.data.model.Movie
import io.erikrios.github.canvaskitmovie.data.model.TvShow

class CinemaDiffCallback<T>(private val oldCinemas: List<T>, private val newCinemas: List<T>) :
    DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldCinemas.size

    override fun getNewListSize(): Int = newCinemas.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        when (oldCinemas[oldItemPosition]) {
            is Movie ->
                (oldCinemas[oldItemPosition] as Movie).id == (newCinemas[newItemPosition] as Movie).id
            is TvShow -> (oldCinemas[oldItemPosition] as TvShow).id == (newCinemas[newItemPosition] as TvShow).id
            else -> throw IllegalArgumentException("Only Movie or TvShow instance are accepted.")
        }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        when (oldCinemas[oldItemPosition]) {
            is Movie ->
                (oldCinemas[oldItemPosition] as Movie) == (newCinemas[newItemPosition] as Movie)
            is TvShow -> (oldCinemas[oldItemPosition] as TvShow) == (newCinemas[newItemPosition] as TvShow)
            else -> throw IllegalArgumentException("Only Movie or TvShow instance are accepted.")
        }
}