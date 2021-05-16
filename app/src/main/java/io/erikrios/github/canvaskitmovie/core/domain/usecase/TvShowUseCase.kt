package io.erikrios.github.canvaskitmovie.core.domain.usecase

import io.erikrios.github.canvaskitmovie.core.data.Resource
import io.erikrios.github.canvaskitmovie.core.domain.model.TvShow
import io.erikrios.github.canvaskitmovie.core.utils.SortUtils
import kotlinx.coroutines.flow.Flow

interface TvShowUseCase {
    fun getTvShows(): Flow<Resource<List<TvShow>>>
    fun getTvShow(id: Int): Flow<Resource<TvShow>>
    fun getFavoriteTvShows(sort: SortUtils.Sort): Flow<List<TvShow>>
    fun setFavoriteTvShow(tvShow: TvShow, state: Boolean)
}