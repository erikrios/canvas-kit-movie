package io.erikrios.github.canvaskitmovie.movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import io.github.erikrios.canvaskitmovie.core.domain.usecase.MovieUseCase

class DiscoverMoviesViewModel(movieUseCase: MovieUseCase) : ViewModel() {
    val moviesState = movieUseCase.getMovies().asLiveData()
}