package io.erikrios.github.canvaskitmovie.core.domain.usecase

import io.erikrios.github.canvaskitmovie.core.domain.repository.ITrendingRepository

class TrendingInteractor(private val trendingRepository: ITrendingRepository) : TrendingUseCase {
    override suspend fun getTrending() = trendingRepository.getTrending()
}