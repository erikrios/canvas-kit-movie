package io.erikrios.github.canvaskitmovie.core.domain.usecase

import androidx.paging.DataSource
import io.erikrios.github.canvaskitmovie.core.data.Resource
import io.erikrios.github.canvaskitmovie.core.domain.model.Movie
import io.erikrios.github.canvaskitmovie.core.utils.SortUtils

interface MovieUseCase {
    suspend fun getMovies(): Resource<List<Movie>>
    suspend fun getMovieById(id: Int): Resource<Movie>
    suspend fun insertFavoriteMovie(movie: Movie): Long
    fun getFavoriteMovies(sort: SortUtils.Sort): DataSource.Factory<Int, Movie>
    suspend fun getFavoriteMovie(id: Int): Movie?
    suspend fun deleteFavoriteMovie(movie: Movie): Int
}