package io.erikrios.github.canvaskitmovie.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import io.erikrios.github.canvaskitmovie.MainCoroutineRule
import io.erikrios.github.canvaskitmovie.data.model.Movie
import io.erikrios.github.canvaskitmovie.data.model.TvShow
import io.erikrios.github.canvaskitmovie.data.source.local.LocalDataSource
import io.erikrios.github.canvaskitmovie.data.source.remote.RemoteDataSource
import io.erikrios.github.canvaskitmovie.utils.DummyData
import io.erikrios.github.canvaskitmovie.utils.NetworkHelper
import io.erikrios.github.canvaskitmovie.utils.Resource
import io.erikrios.github.canvaskitmovie.utils.Status
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class CinemaRepositoryImplTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    private lateinit var repository: FakeCinemaRepositoryImpl

    @Mock
    private lateinit var networkHelper: NetworkHelper

    @Mock
    private lateinit var localDataSource: LocalDataSource

    @Mock
    private lateinit var remoteDataSource: RemoteDataSource

    private lateinit var actualMovies: List<Movie>
    private lateinit var actualTvShows: List<TvShow>
    private var actualMoviesSize: Int = 0
    private var actualTvShowsSize: Int = 0
    private var randomMovieId = 0
    private var randomTvShowId = 0
    private var notExistMovieId = -1
    private var notExistTvShowId = -1
    private var actualDummyMovie: Movie? = null
    private var actualDummyTvShow: TvShow? = null

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        actualMovies = DummyData.generateMovies()
        actualTvShows = DummyData.generateTvShows()
        actualMoviesSize = actualMovies.size
        actualTvShowsSize = actualTvShows.size
        randomMovieId = actualMovies.random().id
        randomTvShowId = actualTvShows.random().id
        actualDummyMovie = DummyData.getMovieById(randomMovieId)
        actualDummyTvShow = DummyData.getTvShowById(randomTvShowId)

        `when`(networkHelper.isNetworkConnected()).thenReturn(true)

        runBlockingTest {
            `when`(localDataSource.getMovies()).thenReturn(Resource.success(actualMovies))
            `when`(localDataSource.getTvShows()).thenReturn(Resource.success(actualTvShows))
            `when`(localDataSource.getMovieDetails(randomMovieId)).thenReturn(
                Resource.success(
                    actualDummyMovie
                )
            )
            `when`(localDataSource.getTvShowDetails(randomTvShowId)).thenReturn(
                Resource.success(
                    actualDummyTvShow
                )
            )
            `when`(localDataSource.getTrending()).thenReturn(Resource.success(actualMovies))
            `when`(remoteDataSource.getMovies()).thenReturn(Resource.success(actualMovies))
            `when`(remoteDataSource.getTvShows()).thenReturn(Resource.success(actualTvShows))
            `when`(remoteDataSource.getMovieDetails(randomMovieId)).thenReturn(
                Resource.success(
                    actualDummyMovie
                )
            )
            `when`(remoteDataSource.getTvShowDetails(randomTvShowId)).thenReturn(
                Resource.success(
                    actualDummyTvShow
                )
            )
            `when`(remoteDataSource.getMovieDetails(notExistMovieId)).thenReturn(
                Resource.error(
                    "Internal server error.", null
                )
            )
            `when`(remoteDataSource.getTvShowDetails(notExistTvShowId)).thenReturn(
                Resource.error(
                    "Internal server error.", null
                )
            )
            `when`(remoteDataSource.getTrending()).thenReturn(Resource.success(actualMovies))
        }

        repository = FakeCinemaRepositoryImpl(networkHelper, localDataSource, remoteDataSource)
    }

    /**
     * -------------------- Get movies test ------------------------------
     */
    @Test
    fun `get movies, returns not null`() = runBlockingTest {
        val moviesResources = repository.getMovies()
        // Verity that remoteDataSource.getMovies() is called
        verify(remoteDataSource).getMovies()
        // Verify that the return value is not null
        assertThat(moviesResources).isNotNull()
    }

    @Test
    fun `get movies, return the list of movies`() = runBlockingTest {
        val moviesResources = repository.getMovies()
        // Verity that remoteDataSource.getMovies() is called
        verify(remoteDataSource).getMovies()
        // Verify that the return value are the list of movies
        assertThat(moviesResources.data).isNotEmpty()
    }

    @Test
    fun `get movies, return the same size with the actual dummy data`() = runBlockingTest {
        val moviesResources = repository.getMovies()
        // Verify that remoteDataSource.getMovies() is called
        verify(remoteDataSource).getMovies()
        // Verify that the return value have the same size with the actual dummy data
        assertThat(moviesResources.data?.size).isEqualTo(actualMoviesSize)
    }

    @Test
    fun `get movies, returns the same elements with the actual dummy data`() = runBlockingTest {
        val moviesResources = repository.getMovies()
        // Verify that remoteDataSource.getMovies() is called
        verify(remoteDataSource).getMovies()
        // Verify that the return value have the same element with the actual dummy data
        assertThat(moviesResources.data).isEqualTo(actualMovies)
    }

    @Test
    fun `get movies when network connected, return resource instance with success status`() =
        runBlockingTest {
            val moviesResources = repository.getMovies()
            // Verify that remoteDataSource.getMovies() is called
            verify(remoteDataSource).getMovies()
            // Verify that the resource instance have success status
            assertThat(moviesResources.status).isEqualTo(Status.SUCCESS)
        }

    @Test
    fun `get movies when network disconnected, return the movies data from local data source`() =
        runBlockingTest {
            // Disable the network connection
            `when`(networkHelper.isNetworkConnected()).thenReturn(false)
            val movieResource = repository.getMovies()
            // Verify that localDataSource.getMovies() is called
            verify(localDataSource).getMovies()
            // Verity that the return value from local data source is not empty
            assertThat(movieResource.data).isNotEmpty()
            // Verity that the status is success
            assertThat(movieResource.status).isEqualTo(Status.SUCCESS)
        }

    @Test
    fun `get movies when network disconnected and the local data source is empty, return error status`() =
        runBlockingTest {
            // Disable the network connection
            `when`(networkHelper.isNetworkConnected()).thenReturn(false)
            // Suppose that local data store is empty
            `when`(localDataSource.getMovies()).thenReturn(Resource.success(listOf()))
            val movieResource = repository.getMovies()
            // Verify that localDataSource.getMovies() is called
            verify(localDataSource).getMovies()
            // Verity that the status is error
            assertThat(movieResource.status).isEqualTo(Status.ERROR)
        }
    /**
     * -------------------- End of get movies test ------------------------------
     */

    /**
     * -------------------- Get tv shows test ------------------------------
     */
    @Test
    fun `get tv shows, returns not null`() = runBlockingTest {
        val tvShowsResource = repository.getTvShows()
        // Verity that remoteDataSource.getTvShows() is called
        verify(remoteDataSource).getTvShows()
        // Verify that the return value is not null
        assertThat(tvShowsResource).isNotNull()
    }

    @Test
    fun `tv shows, return the list of tv shows`() = runBlockingTest {
        val tvShowsResource = repository.getTvShows()
        // Verity that remoteDataSource.getTvShows() is called
        verify(remoteDataSource).getTvShows()
        // Verify that the return value are the list of movies
        assertThat(tvShowsResource.data).isNotEmpty()
    }

    @Test
    fun `get tv shows, return the same size with the actual dummy data`() = runBlockingTest {
        val tvShowsResource = repository.getTvShows()
        // Verify that remoteDataSource.getTvShows() is called
        verify(remoteDataSource).getTvShows()
        // Verify that the return value have the same size with the actual dummy data
        assertThat(tvShowsResource.data?.size).isEqualTo(actualMoviesSize)
    }

    @Test
    fun `get tv shows, returns the same elements with the actual dummy data`() = runBlockingTest {
        val tvShowsResource = repository.getTvShows()
        // Verify that remoteDataSource.getTvShows() is called
        verify(remoteDataSource).getTvShows()
        // Verify that the return value have the same element with the actual dummy data
        assertThat(tvShowsResource.data).isEqualTo(actualTvShows)
    }

    @Test
    fun `get tv shows when network connected, return resource instance with success status`() =
        runBlockingTest {
            val tvShowsResource = repository.getTvShows()
            // Verify that remoteDataSource.getTvShows() is called
            verify(remoteDataSource).getTvShows()
            // Verify that the resource instance have success status
            assertThat(tvShowsResource.status).isEqualTo(Status.SUCCESS)
        }

    @Test
    fun `get tv shows when network disconnected, return the tv shows data from local data source`() =
        runBlockingTest {
            // Disable the network connection
            `when`(networkHelper.isNetworkConnected()).thenReturn(false)
            val tvShowsResource = repository.getTvShows()
            // Verify that localDataSource.getTvShows() is called
            verify(localDataSource).getTvShows()
            // Verity that the return value from local data source is not empty
            assertThat(tvShowsResource.data).isNotEmpty()
            // Verity that the status is success
            assertThat(tvShowsResource.status).isEqualTo(Status.SUCCESS)
        }

    @Test
    fun `get tv shows when network disconnected and the local data source is empty, return error status`() =
        runBlockingTest {
            // Disable the network connection
            `when`(networkHelper.isNetworkConnected()).thenReturn(false)
            // Suppose that local data store is empty
            `when`(localDataSource.getTvShows()).thenReturn(Resource.success(listOf()))
            val tvShowsResource = repository.getTvShows()
            // Verify that localDataSource.getTvShows() is called
            verify(localDataSource).getTvShows()
            // Verity that the status is error
            assertThat(tvShowsResource.status).isEqualTo(Status.ERROR)
        }
    /**
     * -------------------- End of get tv shows test ------------------------------
     */

    /**
     * -------------------- Get movie by id test ------------------------------
     */
    @Test
    fun `get movie with not exist id, returns error status`() = runBlockingTest {
        val movieResource = repository.getMovieById(notExistMovieId)
        // Verify that remoteDataSource.getMovieDetails() is called
        verify(remoteDataSource).getMovieDetails(notExistMovieId)
        // Verity that the status is error
        assertThat(movieResource.status).isEqualTo(Status.ERROR)
    }

    @Test
    fun `get movie with valid id, returns a valid movie with success status`() = runBlockingTest {
        val movieResource = repository.getMovieById(randomMovieId)
        // Verify that remoteDataSource.getMovieDetails() is called
        verify(remoteDataSource).getMovieDetails(randomMovieId)
        // Verity that the movie is valid
        assertThat(movieResource.data).isEqualTo(actualDummyMovie)
        // Verity that the status is success
        assertThat(movieResource.status).isEqualTo(Status.SUCCESS)
    }

    @Test
    fun `get movie when network disconnected, return the data from local data source with success status`() =
        runBlockingTest {
            // Disable the network connection
            `when`(networkHelper.isNetworkConnected()).thenReturn(false)
            val movieResource = repository.getMovieById(randomMovieId)
            // Verify that localDataSource.getMovieDetails() is called
            verify(localDataSource).getMovieDetails(randomMovieId)
            // Verify that the movie is valid
            assertThat(movieResource.data).isEqualTo(actualDummyMovie)
            // Verity that the status is success
            assertThat(movieResource.status).isEqualTo(Status.SUCCESS)
        }

    /**
     * -------------------- End of get movie by id test ------------------------------
     */

    /**
     * -------------------- Get tv show by id test ------------------------------
     */
    @Test
    fun `get tv show with not exist id, returns error status`() = runBlockingTest {
        val tvShowResource = repository.getTvShowById(notExistTvShowId)
        // Verify that remoteDataSource.getTvShowById() is called
        verify(remoteDataSource).getTvShowDetails(notExistTvShowId)
        // Verity that the status is error
        assertThat(tvShowResource.status).isEqualTo(Status.ERROR)
    }

    @Test
    fun `get tv show with valid id, returns a tv show movie with success status`() =
        runBlockingTest {
            val tvShowResource = repository.getTvShowById(randomTvShowId)
            // Verify that remoteDataSource.getTvShowDetails() is called
            verify(remoteDataSource).getTvShowDetails(randomTvShowId)
            // Verity that the tv show is valid
            assertThat(tvShowResource.data).isEqualTo(actualDummyTvShow)
            // Verity that the status is success
            assertThat(tvShowResource.status).isEqualTo(Status.SUCCESS)
        }

    @Test
    fun `get tv show when network disconnected, return the data from local data source with success status`() =
        runBlockingTest {
            // Disable the network connection
            `when`(networkHelper.isNetworkConnected()).thenReturn(false)
            val tvShowResource = repository.getTvShowById(randomTvShowId)
            // Verify that localDataSource.getTvShowDetails() is called
            verify(localDataSource).getTvShowDetails(randomTvShowId)
            // Verify that the tv show is valid
            assertThat(tvShowResource.data).isEqualTo(actualDummyTvShow)
            // Verity that the status is success
            assertThat(tvShowResource.status).isEqualTo(Status.SUCCESS)
        }

    /**
     * -------------------- End of get tv show by id test ------------------------------
     */

    /**
     * -------------------- Get trending test ------------------------------
     */
    @Test
    fun `get trending, returns not null`() = runBlockingTest {
        val moviesResources = repository.getTrending()
        // Verity that remoteDataSource.getTrending() is called
        verify(remoteDataSource).getTrending()
        // Verify that the return value is not null
        assertThat(moviesResources).isNotNull()
    }

    @Test
    fun `get trending, return the list of movies`() = runBlockingTest {
        val moviesResources = repository.getTrending()
        // Verity that remoteDataSource.getTrending() is called
        verify(remoteDataSource).getTrending()
        // Verify that the return value are the list of movies
        assertThat(moviesResources.data).isNotEmpty()
    }

    @Test
    fun `get trending, return the same size with the actual dummy data`() = runBlockingTest {
        val moviesResources = repository.getTrending()
        // Verify that remoteDataSource.getTrending() is called
        verify(remoteDataSource).getTrending()
        // Verify that the return value have the same size with the actual dummy data
        assertThat(moviesResources.data?.size).isEqualTo(actualMoviesSize)
    }

    @Test
    fun `get trending, returns the same elements with the actual dummy data`() = runBlockingTest {
        val moviesResources = repository.getTrending()
        // Verify that remoteDataSource.getTrending() is called
        verify(remoteDataSource).getTrending()
        // Verify that the return value have the same element with the actual dummy data
        assertThat(moviesResources.data).isEqualTo(actualMovies)
    }

    @Test
    fun `get trending when network connected, return resource instance with success status`() =
        runBlockingTest {
            val moviesResources = repository.getTrending()
            // Verify that remoteDataSource.getTrending() is called
            verify(remoteDataSource).getTrending()
            // Verify that the resource instance have success status
            assertThat(moviesResources.status).isEqualTo(Status.SUCCESS)
        }

    @Test
    fun `get trending when network disconnected, return the movies data from local data source`() =
        runBlockingTest {
            // Disable the network connection
            `when`(networkHelper.isNetworkConnected()).thenReturn(false)
            val movieResource = repository.getTrending()
            // Verify that localDataSource.getMovies() is called
            verify(localDataSource).getTrending()
            // Verity that the return value from local data source is not empty
            assertThat(movieResource.data).isNotEmpty()
            // Verity that the status is success
            assertThat(movieResource.status).isEqualTo(Status.SUCCESS)
        }

    @Test
    fun `get trending when network disconnected and the local data source is empty, return error status`() =
        runBlockingTest {
            // Disable the network connection
            `when`(networkHelper.isNetworkConnected()).thenReturn(false)
            // Suppose that local data store is empty
            `when`(localDataSource.getTrending()).thenReturn(Resource.success(listOf()))
            val movieResource = repository.getTrending()
            // Verify that localDataSource.getMovies() is called
            verify(localDataSource).getTrending()
            // Verity that the status is error
            assertThat(movieResource.status).isEqualTo(Status.ERROR)
        }
    /**
     * -------------------- End of get trending test ------------------------------
     */
}