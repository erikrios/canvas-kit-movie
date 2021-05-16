package io.erikrios.github.canvaskitmovie.core.domain.usecase

import io.erikrios.github.canvaskitmovie.core.domain.model.Movie
import io.erikrios.github.canvaskitmovie.core.domain.repository.IMovieRepository
import io.erikrios.github.canvaskitmovie.core.utils.SortUtils

class MovieInteractor(private val movieRepository: IMovieRepository) : MovieUseCase {

    override fun getMovies() = movieRepository.getMovies()

    override fun getMovie(id: Int) = movieRepository.getMovie(id)

    override fun getFavoriteMovies(sort: SortUtils.Sort) = movieRepository.getFavoriteMovies(sort)

    override fun setFavoriteMovie(movie: Movie, state: Boolean) =
        movieRepository.setFavoriteMovie(movie, state)
}