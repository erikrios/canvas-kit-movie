package io.erikrios.github.canvaskitmovie.trending

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import io.erikrios.github.canvaskitmovie.core.domain.usecase.TrendingUseCase

class DiscoverTrendingViewModel(trendingUseCase: TrendingUseCase) : ViewModel() {
    val trendingsState = trendingUseCase.getTrendings().asLiveData()
}