package io.erikrios.github.canvaskitmovie.core.domain.repository

import io.erikrios.github.canvaskitmovie.core.data.Resource
import io.erikrios.github.canvaskitmovie.core.domain.model.TvShow
import io.erikrios.github.canvaskitmovie.core.utils.SortUtils
import kotlinx.coroutines.flow.Flow

interface ITvShowRepository {
    fun getTvShows(): Flow<Resource<List<TvShow>>>
    fun getTvSho(id: Int): Flow<Resource<TvShow>>
    fun getFavoriteTvShows(sort: SortUtils.Sort): Flow<List<TvShow>>
    fun setFavoriteTvSho(tvShow: TvShow, state: Boolean)
}