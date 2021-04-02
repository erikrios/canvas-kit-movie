package io.erikrios.github.canvaskitmovie.data.repository

import io.erikrios.github.canvaskitmovie.data.model.Movie
import io.erikrios.github.canvaskitmovie.data.model.TvShow

interface CinemaRepository {
    suspend fun getMovies(): List<Movie>
    suspend fun getTvShows(): List<TvShow>
    suspend fun getMovieById(id: Int): Movie?
    suspend fun getTvShowById(id: Int): TvShow?
}