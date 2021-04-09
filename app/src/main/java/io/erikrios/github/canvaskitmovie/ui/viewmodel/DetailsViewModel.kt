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

    private val _isFavoriteMovieExistsState = MutableLiveData<Boolean>().apply {
        value = false
    }

    val isFavoriteMovieExistsState: LiveData<Boolean> get() = _isFavoriteMovieExistsState

    private val _isFavoriteTvShowExistsState = MutableLiveData<Boolean>().apply {
        value = false
    }

    val isFavoriteTvShowExistsState: LiveData<Boolean> get() = _isFavoriteTvShowExistsState

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
            if (affectedRows > 0) _isFavoriteMovieExistsState.value = true
        }
    }

    fun isFavoriteMovieExists(id: Int): Job {
        return viewModelScope.launch {
            val movie = repository.getFavoriteMovie(id)
            movie?.let { _isFavoriteMovieExistsState.value = true }
                ?: run { _isFavoriteMovieExistsState.value = false }
        }
    }

    fun deleteFavoriteMovie(movie: Movie): Job {
        return viewModelScope.launch {
            val affectedRows = repository.deleteFavoriteMovie(movie)
            if (affectedRows > 0) _isFavoriteMovieExistsState.value = false
        }
    }

    fun insertFavoriteTvShow(tvShow: TvShow): Job {
        return viewModelScope.launch {
            val affectedRows = repository.insertFavoriteTvShow(tvShow)
            if (affectedRows > 0) _isFavoriteTvShowExistsState.value = true
        }
    }

    fun isFavoriteTvShowExists(id: Int): Job {
        return viewModelScope.launch {
            val tvShow = repository.getFavoriteTvShow(id)
            tvShow?.let { _isFavoriteTvShowExistsState.value = true }
                ?: run { _isFavoriteTvShowExistsState.value = false }
        }
    }

    fun deleteFavoriteTvShow(tvShow: TvShow): Job {
        return viewModelScope.launch {
            val affectedRows = repository.deleteFavoriteTvShow(tvShow)
            if (affectedRows > 0) _isFavoriteTvShowExistsState.value = false
        }
    }
}