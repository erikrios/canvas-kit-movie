package io.erikrios.github.canvaskitmovie.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.erikrios.github.canvaskitmovie.data.model.Movie
import io.erikrios.github.canvaskitmovie.data.model.TvShow
import io.erikrios.github.canvaskitmovie.data.repository.CinemaRepository
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: CinemaRepository) : ViewModel() {

    private val _moviesState = MutableLiveData<List<Movie>>().apply {
        value = listOf()
    }
    val moviesState: LiveData<List<Movie>> get() = _moviesState

    private val _tvShowsState = MutableLiveData<List<TvShow>>().apply {
        value = listOf()
    }
    val tvShowsState: LiveData<List<TvShow>> get() = _tvShowsState

    private val _movieState = MutableLiveData<Movie?>().apply {
        value = null
    }

    val movieState: LiveData<Movie?> get() = _movieState

    private val _tvShowState = MutableLiveData<TvShow?>().apply {
        value = null
    }

    val tvShowState: LiveData<TvShow?> get() = _tvShowState


    fun getMovies() {
        _moviesState.value = repository.getMovies()
    }

    fun getTvShows() {
        _tvShowsState.value = repository.getTvShows()
    }

    fun getMovieById(id: Int) {
        _movieState.value = repository.getMovieById(id)
    }

    fun getTvShowById(id: Int) {
        _tvShowState.value = repository.getTvShowById(id)
    }
}