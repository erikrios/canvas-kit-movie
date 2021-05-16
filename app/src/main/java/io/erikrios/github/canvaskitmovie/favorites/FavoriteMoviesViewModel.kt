package io.erikrios.github.canvaskitmovie.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import io.erikrios.github.canvaskitmovie.core.domain.usecase.MovieUseCase
import io.erikrios.github.canvaskitmovie.core.utils.SortUtils

class FavoriteMoviesViewModel(private val movieUseCase: MovieUseCase) :
    ViewModel() {

    fun getFavoriteMovies(sort: SortUtils.Sort) = movieUseCase.getFavoriteMovies(sort).asLiveData()
}