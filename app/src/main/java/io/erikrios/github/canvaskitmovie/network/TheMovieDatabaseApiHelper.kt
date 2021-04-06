package io.erikrios.github.canvaskitmovie.network

import io.erikrios.github.canvaskitmovie.data.model.DiscoverResponse
import io.erikrios.github.canvaskitmovie.data.model.Movie
import io.erikrios.github.canvaskitmovie.data.model.TvShow
import retrofit2.Response

interface TheMovieDatabaseApiHelper {
    suspend fun getMovies(): Response<DiscoverResponse<Movie>>
    suspend fun getTvShows(): Response<DiscoverResponse<TvShow>>
    suspend fun getMovieDetails(id: Int): Response<Movie>
    suspend fun getTvShowDetails(id: Int): Response<TvShow>
}