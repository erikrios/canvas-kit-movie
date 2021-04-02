package io.erikrios.github.canvaskitmovie.data.source

import io.erikrios.github.canvaskitmovie.data.model.Movie
import io.erikrios.github.canvaskitmovie.data.model.TvShow
import io.erikrios.github.canvaskitmovie.utils.Resource

interface DataSource {
    fun getMovies(): Resource<List<Movie>>
    fun getTvShows(): Resource<List<TvShow>>
    fun getMovieDetails(id: Int): Resource<Movie>
    fun getTvShowDetails(id: Int): Resource<TvShow>
}