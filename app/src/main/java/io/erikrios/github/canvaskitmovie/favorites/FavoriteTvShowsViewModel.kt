package io.erikrios.github.canvaskitmovie.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import io.erikrios.github.canvaskitmovie.core.domain.usecase.TvShowUseCase
import io.erikrios.github.canvaskitmovie.core.utils.SortUtils

class FavoriteTvShowsViewModel(private val tvShowUseCase: TvShowUseCase) :
    ViewModel() {

    fun getFavoriteTvShows(sort: SortUtils.Sort) =
        tvShowUseCase.getFavoriteTvShows(sort).asLiveData()
}