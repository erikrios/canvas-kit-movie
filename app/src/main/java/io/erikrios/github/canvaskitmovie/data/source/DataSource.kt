package io.erikrios.github.canvaskitmovie.data.source

import io.erikrios.github.canvaskitmovie.data.model.Movie
import io.erikrios.github.canvaskitmovie.data.model.TvShow
import io.erikrios.github.canvaskitmovie.utils.Resource

interface DataSource {
    suspend fun getMovies(): Resource<List<Movie>>
    suspend fun getTvShows(): Resource<List<TvShow>>
    suspend fun getMovieDetails(id: Int): Resource<Movie>
    suspend fun getTvShowDetails(id: Int): Resource<TvShow>
    suspend fun getTrending(): Resource<List<Movie>>
}