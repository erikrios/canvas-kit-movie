package io.erikrios.github.canvaskitmovie.favorites

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import io.erikrios.github.canvaskitmovie.core.domain.model.Movie
import io.erikrios.github.canvaskitmovie.core.domain.model.TvShow
import io.erikrios.github.canvaskitmovie.core.domain.repository.ITrendingRepository
import io.erikrios.github.canvaskitmovie.core.utils.SortUtils

class FavoritesViewModel (private val repository: ITrendingRepository) :
    ViewModel() {
//
//    fun getFavoriteMovies(sort: SortUtils.Sort): LiveData<PagedList<Movie>> =
//        LivePagedListBuilder(repository.getFavoriteMovies(sort), 20).build()
//
//
//    fun getFavoriteTvShows(sort: SortUtils.Sort): LiveData<PagedList<TvShow>> =
//        LivePagedListBuilder(repository.getFavoriteTvShows(sort), 20).build()
}