package io.github.erikrios.canvaskitmovie.core.domain.usecase

import io.github.erikrios.canvaskitmovie.core.domain.model.Trending
import io.github.erikrios.canvaskitmovie.core.domain.repository.ITrendingRepository
import io.github.erikrios.canvaskitmovie.core.utils.SortUtils

class TrendingInteractor(private val trendingRepository: ITrendingRepository) : TrendingUseCase {

    override fun getTrendings() = trendingRepository.getTrendings()

    override fun getTrending(id: Int) = trendingRepository.getTrending(id)

    override fun getFavoriteTrendings(sort: SortUtils.Sort) =
        trendingRepository.getFavoriteTrendings(sort)

    override fun setFavoriteTrending(trending: Trending, state: Boolean) =
        trendingRepository.setFavoriteTrending(trending, state)
}