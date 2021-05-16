package io.github.erikrios.canvaskitmovie.core.domain.usecase

import io.github.erikrios.canvaskitmovie.core.data.Resource
import io.github.erikrios.canvaskitmovie.core.domain.model.Trending
import io.github.erikrios.canvaskitmovie.core.utils.SortUtils
import kotlinx.coroutines.flow.Flow

interface TrendingUseCase {
    fun getTrendings(): Flow<Resource<List<Trending>>>
    fun getTrending(id: Int): Flow<Resource<Trending>>
    fun getFavoriteTrendings(sort: SortUtils.Sort): Flow<List<Trending>>
    fun setFavoriteTrending(trending: Trending, state: Boolean)
}