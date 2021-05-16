package io.github.erikrios.canvaskitmovie.core.domain.usecase

import io.github.erikrios.canvaskitmovie.core.data.Resource
import io.github.erikrios.canvaskitmovie.core.domain.model.Movie
import io.github.erikrios.canvaskitmovie.core.utils.SortUtils
import kotlinx.coroutines.flow.Flow

interface MovieUseCase {
    fun getMovies(): Flow<Resource<List<Movie>>>
    fun getMovie(id: Int): Flow<Resource<Movie>>
    fun getFavoriteMovies(sort: SortUtils.Sort): Flow<List<Movie>>
    fun setFavoriteMovie(movie: Movie, state: Boolean)
}