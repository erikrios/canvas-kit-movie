package io.erikrios.github.canvaskitmovie.network

import io.erikrios.github.canvaskitmovie.data.model.DiscoverResponse
import io.erikrios.github.canvaskitmovie.data.model.Movie
import io.erikrios.github.canvaskitmovie.data.model.TvShow
import retrofit2.Response
import javax.inject.Inject

class TheMovieDatabaseApiHelperImpl @Inject constructor(private val apiService: TheMovieDatabaseApiService) :
    TheMovieDatabaseApiHelper {

    override suspend fun getMovies(): Response<DiscoverResponse<Movie>> = apiService.getMovies()

    override suspend fun getTvShows(): Response<DiscoverResponse<TvShow>> = apiService.getTvShows()

    override suspend fun getMovieDetails(id: Int): Response<Movie> = apiService.getMovieDetails(id)

    override suspend fun getTvShowDetails(id: Int): Response<TvShow> =
        apiService.getTvShowDetails(id)

    override suspend fun getTrending(): Response<DiscoverResponse<Movie>> = apiService.getTrending()
}