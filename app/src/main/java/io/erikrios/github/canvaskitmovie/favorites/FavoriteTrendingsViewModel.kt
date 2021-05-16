package io.erikrios.github.canvaskitmovie.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import io.erikrios.github.canvaskitmovie.core.domain.usecase.TrendingUseCase
import io.erikrios.github.canvaskitmovie.core.utils.SortUtils

class FavoriteTrendingsViewModel(private val trendingUseCase: TrendingUseCase) :
    ViewModel() {

    fun getFavoriteTrendings(sort: SortUtils.Sort) =
        trendingUseCase.getFavoriteTrendings(sort).asLiveData()
}