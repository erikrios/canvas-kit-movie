package io.erikrios.github.canvaskitmovie.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import io.github.erikrios.canvaskitmovie.core.domain.usecase.TrendingUseCase
import io.github.erikrios.canvaskitmovie.core.utils.SortUtils

class FavoriteTrendingsViewModel(private val trendingUseCase: TrendingUseCase) :
    ViewModel() {

    fun getFavoriteTrendings(sort: SortUtils.Sort) =
        trendingUseCase.getFavoriteTrendings(sort).asLiveData()
}