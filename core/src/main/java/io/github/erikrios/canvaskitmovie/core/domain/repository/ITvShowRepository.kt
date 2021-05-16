package io.github.erikrios.canvaskitmovie.core.domain.repository

import io.github.erikrios.canvaskitmovie.core.data.Resource
import io.github.erikrios.canvaskitmovie.core.domain.model.TvShow
import io.github.erikrios.canvaskitmovie.core.utils.SortUtils
import kotlinx.coroutines.flow.Flow

interface ITvShowRepository {
    fun getTvShows(): Flow<Resource<List<TvShow>>>
    fun getTvShow(id: Int): Flow<Resource<TvShow>>
    fun getFavoriteTvShows(sort: SortUtils.Sort): Flow<List<TvShow>>
    fun setFavoriteTvShow(tvShow: TvShow, state: Boolean)
}