package io.erikrios.github.canvaskitmovie.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import io.erikrios.github.canvaskitmovie.core.domain.model.TvShow
import io.erikrios.github.canvaskitmovie.core.domain.usecase.TvShowUseCase

class TvShowDetailsViewModel(private val tvShowUseCase: TvShowUseCase) : ViewModel() {
    fun getTvShow(id: Int) = tvShowUseCase.getTvShow(id).asLiveData()

    fun setFavoriteMovie(tvShow: TvShow, newStatus: Boolean) =
        tvShowUseCase.setFavoriteTvShow(tvShow, newStatus)
}