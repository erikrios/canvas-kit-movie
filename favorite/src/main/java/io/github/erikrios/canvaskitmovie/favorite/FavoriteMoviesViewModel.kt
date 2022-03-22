package io.github.erikrios.canvaskitmovie.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import io.github.erikrios.canvaskitmovie.core.domain.usecase.MovieUseCase
import io.github.erikrios.canvaskitmovie.core.utils.SortUtils

class FavoriteMoviesViewModel(private val movieUseCase: MovieUseCase) :
    ViewModel() {
    fun getFavoriteMovies(sort: SortUtils.Sort) = movieUseCase.getFavoriteMovies(sort).asLiveData()
}