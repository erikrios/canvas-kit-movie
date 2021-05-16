package io.erikrios.github.canvaskitmovie.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import io.erikrios.github.canvaskitmovie.core.domain.model.Trending
import io.erikrios.github.canvaskitmovie.core.domain.usecase.TrendingUseCase

class TrendingDetailsViewModel(private val trendingUseCase: TrendingUseCase) : ViewModel() {
    fun getMovie(id: Int) = trendingUseCase.getTrending(id).asLiveData()

    fun setFavoriteMovie(trending: Trending, newStatus: Boolean) =
        trendingUseCase.setFavoriteTrending(trending, newStatus)
}