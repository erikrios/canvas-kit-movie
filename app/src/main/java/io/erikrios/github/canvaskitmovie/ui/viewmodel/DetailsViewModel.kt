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
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(private val repository: CinemaRepository) : ViewModel() {

    private val _movieState = MutableLiveData<Resource<Movie>>().apply {
        value = Resource.loading(null)
    }

    val movieState: LiveData<Resource<Movie>> get() = _movieState

    private val _tvShowState = MutableLiveData<Resource<TvShow>>().apply {
        value = Resource.loading(null)
    }

    val tvShowState: LiveData<Resource<TvShow>> get() = _tvShowState

    private val _isFavoriteMovieExists = MutableLiveData<Boolean>().apply {
        value = false
    }

    fun getMovieById(id: Int): Job {
        return viewModelScope.launch {
            _movieState.value = Resource.loading(null)
            _movieState.value = repository.getMovieById(id)
        }
    }

    fun getTvShowById(id: Int): Job {
        return viewModelScope.launch {
            _movieState.value = Resource.loading(null)
            _tvShowState.value = repository.getTvShowById(id)
        }
    }

    fun insertFavoriteMovie(movie: Movie): Job {
        return viewModelScope.launch {
            val affectedRows = repository.insertFavoriteMovie(movie)
            if (affectedRows > 0) _isFavoriteMovieExists.value = true
        }
    }
}