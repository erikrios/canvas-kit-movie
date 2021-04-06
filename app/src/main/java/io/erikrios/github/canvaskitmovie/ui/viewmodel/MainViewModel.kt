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
class MainViewModel @Inject constructor(private val repository: CinemaRepository) : ViewModel() {

    private val _moviesState = MutableLiveData<Resource<List<Movie>>>().apply {
        value = Resource.loading(null)
    }
    val moviesState: LiveData<Resource<List<Movie>>> get() = _moviesState

    private val _tvShowsState = MutableLiveData<Resource<List<TvShow>>>().apply {
        value = Resource.loading(null)
    }
    val tvShowsState: LiveData<Resource<List<TvShow>>> get() = _tvShowsState

    fun getMovies(): Job {
        return viewModelScope.launch {
            _moviesState.value = Resource.loading(null)
            _moviesState.value = repository.getMovies()
        }
    }

    fun getTvShows(): Job {
        return viewModelScope.launch {
            _tvShowsState.value = Resource.loading(null)
            _tvShowsState.value = repository.getTvShows()
        }
    }
}