package io.erikrios.github.canvaskitmovie.core.domain.repository

import io.erikrios.github.canvaskitmovie.core.data.Resource
import io.erikrios.github.canvaskitmovie.core.domain.model.Trending
import io.erikrios.github.canvaskitmovie.core.utils.SortUtils
import kotlinx.coroutines.flow.Flow

interface ITrendingRepository {
    fun getTrendings(): Flow<Resource<List<Trending>>>
    fun getTrending(id: Int): Flow<Resource<Trending>>
    fun getFavoriteTrendings(sort: SortUtils.Sort): Flow<List<Trending>>
    fun setFavoriteTrending(trending: Trending, state: Boolean)
}