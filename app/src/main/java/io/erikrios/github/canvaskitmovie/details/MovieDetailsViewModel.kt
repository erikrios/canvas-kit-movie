package io.erikrios.github.canvaskitmovie.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import io.github.erikrios.canvaskitmovie.core.domain.model.Movie
import io.github.erikrios.canvaskitmovie.core.domain.usecase.MovieUseCase

class MovieDetailsViewModel(private val movieUseCase: MovieUseCase) : ViewModel() {

    fun getMovie(id: Int) = movieUseCase.getMovie(id).asLiveData()

    fun setFavoriteMovie(movie: Movie, newStatus: Boolean) =
        movieUseCase.setFavoriteMovie(movie, newStatus)
}