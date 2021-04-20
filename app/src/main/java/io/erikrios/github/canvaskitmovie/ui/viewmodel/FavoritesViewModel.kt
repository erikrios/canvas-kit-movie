package io.erikrios.github.canvaskitmovie.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import dagger.hilt.android.lifecycle.HiltViewModel
import io.erikrios.github.canvaskitmovie.data.model.Movie
import io.erikrios.github.canvaskitmovie.data.model.TvShow
import io.erikrios.github.canvaskitmovie.data.repository.CinemaRepository
import io.erikrios.github.canvaskitmovie.utils.SortUtils
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(private val repository: CinemaRepository) :
    ViewModel() {

    fun getFavoriteMovies(sort: SortUtils.Sort): LiveData<PagedList<Movie>> =
        LivePagedListBuilder(repository.getFavoriteMovies(sort), 20).build()


    fun getFavoriteTvShows(sort: SortUtils.Sort): LiveData<PagedList<TvShow>> =
        LivePagedListBuilder(repository.getFavoriteTvShows(sort), 20).build()
}