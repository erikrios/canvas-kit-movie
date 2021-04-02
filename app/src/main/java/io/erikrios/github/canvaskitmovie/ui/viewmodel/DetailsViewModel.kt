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
class DetailsViewModel @Inject constructor(private val repository: CinemaRepository) : ViewModel() {

    private val _movieState = MutableLiveData<Movie?>().apply {
        value = null
    }

    val movieState: LiveData<Movie?> get() = _movieState

    private val _tvShowState = MutableLiveData<TvShow?>().apply {
        value = null
    }

    val tvShowState: LiveData<TvShow?> get() = _tvShowState

    fun getMovieById(id: Int) {
        _movieState.value = repository.getMovieById(id)
    }

    fun getTvShowById(id: Int) {
        _tvShowState.value = repository.getTvShowById(id)
    }
}