package io.erikrios.github.canvaskitmovie.core.domain.usecase

import io.erikrios.github.canvaskitmovie.core.domain.model.TvShow
import io.erikrios.github.canvaskitmovie.core.domain.repository.ITvShowRepository
import io.erikrios.github.canvaskitmovie.core.utils.SortUtils

class TvShowInteractor(private val tvShowRepository: ITvShowRepository) : TvShowUseCase {

    override fun getTvShows() = tvShowRepository.getTvShows()

    override fun getTvShow(id: Int) = tvShowRepository.getTvShow(id)

    override fun getFavoriteTvShows(sort: SortUtils.Sort) =
        tvShowRepository.getFavoriteTvShows(sort)

    override fun setFavoriteTvShow(tvShow: TvShow, state: Boolean) =
        tvShowRepository.setFavoriteTvShow(tvShow, state)
}