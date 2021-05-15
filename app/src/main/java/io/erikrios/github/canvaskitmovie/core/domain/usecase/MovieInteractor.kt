package io.erikrios.github.canvaskitmovie.core.domain.usecase

import io.erikrios.github.canvaskitmovie.core.domain.model.Movie
import io.erikrios.github.canvaskitmovie.core.domain.repository.IMovieRepository
import io.erikrios.github.canvaskitmovie.core.utils.SortUtils

class MovieInteractor(private val movieRepository: IMovieRepository) : MovieUseCase {
    override suspend fun getMovies() = movieRepository.getMovies()

    override suspend fun getMovieById(id: Int) = movieRepository.getMovieById(id)

    override suspend fun insertFavoriteMovie(movie: Movie) =
        movieRepository.insertFavoriteMovie(movie)

    override fun getFavoriteMovies(sort: SortUtils.Sort) = movieRepository.getFavoriteMovies(sort)

    override suspend fun getFavoriteMovie(id: Int) = movieRepository.getFavoriteMovie(id)

    override suspend fun deleteFavoriteMovie(movie: Movie) =
        movieRepository.deleteFavoriteMovie(movie)
}