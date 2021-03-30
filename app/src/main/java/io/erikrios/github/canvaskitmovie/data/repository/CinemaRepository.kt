package io.erikrios.github.canvaskitmovie.data.repository

import io.erikrios.github.canvaskitmovie.data.model.Movie
import io.erikrios.github.canvaskitmovie.data.model.TvShow

interface CinemaRepository {
    fun getMovies(): List<Movie>
    fun getTvShows(): List<TvShow>
    fun getMovieById(): Movie?
    fun getTvShowById(): TvShow?
}