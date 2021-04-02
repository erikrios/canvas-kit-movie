package io.erikrios.github.canvaskitmovie.data.repository

import io.erikrios.github.canvaskitmovie.data.model.Movie
import io.erikrios.github.canvaskitmovie.data.model.TvShow
import io.erikrios.github.canvaskitmovie.utils.Resource

interface CinemaRepository {
    suspend fun getMovies(): Resource<List<Movie>>
    suspend fun getTvShows(): Resource<List<TvShow>>
    suspend fun getMovieById(id: Int): Resource<Movie>
    suspend fun getTvShowById(id: Int): Resource<TvShow>
}