package io.github.erikrios.canvaskitmovie.core.domain.usecase

import io.github.erikrios.canvaskitmovie.core.domain.model.Movie
import io.github.erikrios.canvaskitmovie.core.domain.repository.IMovieRepository
import io.github.erikrios.canvaskitmovie.core.utils.SortUtils

class MovieInteractor(private val movieRepository: IMovieRepository) : MovieUseCase {

    override fun getMovies() = movieRepository.getMovies()

    override fun getMovie(id: Int) = movieRepository.getMovie(id)

    override fun getFavoriteMovies(sort: SortUtils.Sort) = movieRepository.getFavoriteMovies(sort)

    override fun setFavoriteMovie(movie: Movie, state: Boolean) =
        movieRepository.setFavoriteMovie(movie, state)
}