package io.github.erikrios.canvaskitmovie.core.data.source.remote.network

class TheMovieDatabaseApiHelperImpl(private val apiService: TheMovieDatabaseApiService) :
    TheMovieDatabaseApiHelper {

    override suspend fun getMovies() = apiService.getMovies()

    override suspend fun getTvShows() = apiService.getTvShows()

    override suspend fun getMovieDetails(id: Int) = apiService.getMovieDetails(id)

    override suspend fun getTvShowDetails(id: Int) = apiService.getTvShowDetails(id)

    override suspend fun getTrending() = apiService.getTrending()

    override suspend fun getTrendingDetails(id: Int) = apiService.getTrendingDetails(id)
}