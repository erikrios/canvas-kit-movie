package io.erikrios.github.canvaskitmovie.core.domain.usecase

import androidx.paging.DataSource
import io.erikrios.github.canvaskitmovie.core.data.Resource
import io.erikrios.github.canvaskitmovie.core.domain.model.TvShow
import io.erikrios.github.canvaskitmovie.core.utils.SortUtils

interface TvShowUseCase {
    suspend fun getTvShows(): Resource<List<TvShow>>
    suspend fun getTvShowById(id: Int): Resource<TvShow>
    suspend fun insertFavoriteTvShow(tvShow: TvShow): Long
    fun getFavoriteTvShows(sort: SortUtils.Sort): DataSource.Factory<Int, TvShow>
    suspend fun getFavoriteTvShow(id: Int): TvShow?
    suspend fun deleteFavoriteTvShow(tvShow: TvShow): Int
}