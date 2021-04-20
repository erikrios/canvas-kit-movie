package io.erikrios.github.canvaskitmovie.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.erikrios.github.canvaskitmovie.data.model.Movie
import io.erikrios.github.canvaskitmovie.data.model.TvShow
import io.erikrios.github.canvaskitmovie.data.repository.CinemaRepository
import io.erikrios.github.canvaskitmovie.utils.Resource
import io.erikrios.github.canvaskitmovie.utils.SortUtils
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(private val repository: CinemaRepository) :
    ViewModel() {

    private val _favoriteMoviesState = MutableLiveData<Resource<List<Movie>>>().apply {
        value = Resource.loading(null)
    }

    val favoriteMoviesState: LiveData<Resource<List<Movie>>> get() = _favoriteMoviesState

    private val _favoriteTvShowsState = MutableLiveData<Resource<List<TvShow>>>().apply {
        value = Resource.loading(null)
    }

    val favoriteTvShowsState: LiveData<Resource<List<TvShow>>> get() = _favoriteTvShowsState

    fun getFavoriteMovies(sort: SortUtils.Sort): Job {
        return viewModelScope.launch {
            val movies = repository.getFavoriteMovies(sort)
            _favoriteMoviesState.value = Resource.success(movies)
        }
    }

    fun getFavoriteTvShows(sort: SortUtils.Sort): Job {
        return viewModelScope.launch {
            val tvShows = repository.getFavoriteTvShows(sort)
            _favoriteTvShowsState.value = Resource.success(tvShows)
        }
    }
}