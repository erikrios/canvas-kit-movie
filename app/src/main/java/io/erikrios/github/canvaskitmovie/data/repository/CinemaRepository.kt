package io.erikrios.github.canvaskitmovie.data.repository

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
    suspend fun getFavoriteMovies(sort: SortUtils.Sort): List<Movie>
    suspend fun getFavoriteMovie(id: Int): Movie?
    suspend fun deleteFavoriteMovie(movie: Movie): Int
    suspend fun insertFavoriteTvShow(tvShow: TvShow): Long
    suspend fun getFavoriteTvShows(sort: SortUtils.Sort): List<TvShow>
    suspend fun getFavoriteTvShow(id: Int): TvShow?
    suspend fun deleteFavoriteTvShow(tvShow: TvShow): Int
    suspend fun getTrending(): Resource<List<Movie>>
}