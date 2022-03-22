package io.github.erikrios.canvaskitmovie.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import io.github.erikrios.canvaskitmovie.core.domain.usecase.TvShowUseCase
import io.github.erikrios.canvaskitmovie.core.utils.SortUtils

class FavoriteTvShowsViewModel(private val tvShowUseCase: TvShowUseCase) :
    ViewModel() {
    fun getFavoriteTvShows(sort: SortUtils.Sort) =
        tvShowUseCase.getFavoriteTvShows(sort).asLiveData()
}