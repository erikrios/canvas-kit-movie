package io.erikrios.github.canvaskitmovie.tvshows

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import io.erikrios.github.canvaskitmovie.core.domain.usecase.TvShowUseCase

class DiscoverTvShowsViewModel(tvShowUseCase: TvShowUseCase) : ViewModel() {
    val tvShowsState = tvShowUseCase.getTvShows().asLiveData()
}