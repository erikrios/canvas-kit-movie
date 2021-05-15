package io.erikrios.github.canvaskitmovie.core.domain.usecase

import io.erikrios.github.canvaskitmovie.core.data.Resource
import io.erikrios.github.canvaskitmovie.core.domain.model.Trending

interface TrendingUseCase {
    suspend fun getTrending(): Resource<List<Trending>>
}