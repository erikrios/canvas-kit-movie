package io.erikrios.github.canvaskitmovie.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import io.github.erikrios.canvaskitmovie.core.domain.model.Trending
import io.github.erikrios.canvaskitmovie.core.domain.usecase.TrendingUseCase

class TrendingDetailsViewModel(private val trendingUseCase: TrendingUseCase) : ViewModel() {

    fun getTrending(id: Int) = trendingUseCase.getTrending(id).asLiveData()

    fun setFavoriteTrending(trending: Trending, newStatus: Boolean) =
        trendingUseCase.setFavoriteTrending(trending, newStatus)
}