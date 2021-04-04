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
}