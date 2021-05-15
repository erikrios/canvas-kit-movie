package io.erikrios.github.canvaskitmovie.core.domain.usecase

import io.erikrios.github.canvaskitmovie.core.domain.model.TvShow
import io.erikrios.github.canvaskitmovie.core.domain.repository.ITvShowRepository
import io.erikrios.github.canvaskitmovie.core.utils.SortUtils

class TvShowInteractor(private val tvShowRepository: ITvShowRepository) : TvShowUseCase {
    override suspend fun getTvShows() = tvShowRepository.getTvShows()

    override suspend fun getTvShowById(id: Int) = tvShowRepository.getTvShowById(id)

    override suspend fun insertFavoriteTvShow(tvShow: TvShow) =
        tvShowRepository.insertFavoriteTvShow(tvShow)

    override fun getFavoriteTvShows(sort: SortUtils.Sort) =
        tvShowRepository.getFavoriteTvShows(sort)

    override suspend fun getFavoriteTvShow(id: Int) = tvShowRepository.getFavoriteTvShow(id)

    override suspend fun deleteFavoriteTvShow(tvShow: TvShow) =
        tvShowRepository.deleteFavoriteTvShow(tvShow)
}