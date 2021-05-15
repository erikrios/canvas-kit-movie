package io.erikrios.github.canvaskitmovie.core.domain.repository

import io.erikrios.github.canvaskitmovie.core.data.Resource
import io.erikrios.github.canvaskitmovie.core.domain.model.Movie
import io.erikrios.github.canvaskitmovie.core.utils.SortUtils
import kotlinx.coroutines.flow.Flow

interface IMovieRepository {
    fun getMovies(): Flow<Resource<List<Movie>>>
    fun getMovie(id: Int): Flow<Resource<Movie>>
    fun getFavoriteMovies(sort: SortUtils.Sort): Flow<List<Movie>>
    fun setFavoriteMovie(movie: Movie, state: Boolean)
}