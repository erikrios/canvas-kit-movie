package io.erikrios.github.canvaskitmovie.data.repository

import androidx.paging.DataSource
import io.erikrios.github.canvaskitmovie.data.model.Movie
import io.erikrios.github.canvaskitmovie.data.model.TvShow
import io.erikrios.github.canvaskitmovie.utils.Resource
import io.erikrios.github.canvaskitmovie.utils.SortUtils

interface CinemaRepository {
    suspend fun getMovies(): Resource<List<Movie>>
    suspend fun getTvShows(): Resource<List<TvShow>>
    suspend fun getMovieById(id: Int): Resource<Movie>
    suspend fun getTvShowById(id: Int): Resource<TvShow>
    suspend fun insertFavoriteMovie(movie: Movie): Long
    fun getFavoriteMovies(sort: SortUtils.Sort): DataSource.Factory<Int, Movie>
    suspend fun getFavoriteMovie(id: Int): Movie?
    suspend fun deleteFavoriteMovie(movie: Movie): Int
    suspend fun insertFavoriteTvShow(tvShow: TvShow): Long
    fun getFavoriteTvShows(sort: SortUtils.Sort): DataSource.Factory<Int, TvShow>
    suspend fun getFavoriteTvShow(id: Int): TvShow?
    suspend fun deleteFavoriteTvShow(tvShow: TvShow): Int
    suspend fun getTrending(): Resource<List<Movie>>
}