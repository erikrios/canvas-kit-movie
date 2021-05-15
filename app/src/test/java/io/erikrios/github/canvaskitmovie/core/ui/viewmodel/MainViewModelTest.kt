package io.erikrios.github.canvaskitmovie.core.ui.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import io.erikrios.github.canvaskitmovie.MainCoroutineRule
import io.erikrios.github.canvaskitmovie.core.domain.model.Movie
import io.erikrios.github.canvaskitmovie.core.domain.model.TvShow
import io.erikrios.github.canvaskitmovie.getOrAwaitValueTest
import io.erikrios.github.canvaskitmovie.main.MainViewModel
import io.erikrios.github.canvaskitmovie.core.utils.DummyData.generateMovies
import io.erikrios.github.canvaskitmovie.core.utils.DummyData.generateTvShows
import io.erikrios.github.canvaskitmovie.core.utils.DummyData.getMovieById
import io.erikrios.github.canvaskitmovie.core.utils.DummyData.getTvShowById
import io.erikrios.github.canvaskitmovie.core.data.Resource
import io.erikrios.github.canvaskitmovie.core.utils.Status
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.verify
import java.util.concurrent.TimeUnit

@ExperimentalCoroutinesApi
class MainViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    @Mock
    private lateinit var repository: CinemaRepositoryImpl

    private lateinit var mainViewModel: MainViewModel
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

        actualMovies = generateMovies()
        actualTvShows = generateTvShows()
        actualMoviesSize = actualMovies.size
        actualTvShowsSize = actualTvShows.size
        randomMovieId = actualMovies.random().id
        randomTvShowId = actualTvShows.random().id
        actualDummyMovie = getMovieById(randomMovieId)
        actualDummyTvShow = getTvShowById(randomTvShowId)

        runBlockingTest {
            `when`(repository.getMovies()).thenReturn(Resource.success(actualMovies))
            `when`(repository.getTvShows()).thenReturn(Resource.success(actualTvShows))
            `when`(repository.getMovieById(randomMovieId)).thenReturn(
                Resource.success(
                    actualDummyMovie
                )
            )
            `when`(repository.getTvShowById(randomTvShowId)).thenReturn(
                Resource.success(
                    actualDummyTvShow
                )
            )
            `when`(repository.getMovieById(notExistMovieId)).thenReturn(
                Resource.error(
                    "Internal server error.", null
                )
            )
            `when`(repository.getTvShowById(notExistTvShowId)).thenReturn(
                Resource.error(
                    "Internal server error.", null
                )
            )
            `when`(repository.getTrending()).thenReturn(Resource.success(actualMovies))
        }

        mainViewModel = MainViewModel(repository)
    }

    /**
     * -------------------- Get movies test ------------------------------
     */
    @Test
    fun `get movies, returns not null`() = runBlockingTest {
        var moviesResource =
            mainViewModel.moviesState.getOrAwaitValueTest(100, TimeUnit.MILLISECONDS)
        // Verify that the initial status is loading
        assertThat(moviesResource.status).isEqualTo(Status.LOADING)
        mainViewModel.getMovies()
        // Verify that repository.getMovies() is called
        verify(repository).getMovies()
        // Verify that the status is success
        moviesResource = mainViewModel.moviesState.getOrAwaitValueTest()
        assertThat(moviesResource.status).isEqualTo(Status.SUCCESS)
        // Verify that the movies data is not null
        assertThat(moviesResource.data).isNotNull()
    }

    @Test
    fun `get movies, return the list of movies`() = runBlockingTest {
        var moviesResource =
            mainViewModel.moviesState.getOrAwaitValueTest(100, TimeUnit.MILLISECONDS)
        // Verify that the initial status is loading
        assertThat(moviesResource.status).isEqualTo(Status.LOADING)
        mainViewModel.getMovies()
        // Verify that repository.getMovies() is called
        verify(repository).getMovies()
        // Verify that the status is success
        moviesResource = mainViewModel.moviesState.getOrAwaitValueTest()
        assertThat(moviesResource.status).isEqualTo(Status.SUCCESS)
        // Verify that the data is the instance of the list of movie / not empty
        assertThat(moviesResource.data).isNotEmpty()
    }

    @Test
    fun `get movies, return the same size with the actual dummy data`() = runBlockingTest {
        var moviesResource =
            mainViewModel.moviesState.getOrAwaitValueTest(100, TimeUnit.MILLISECONDS)
        // Verify that the initial status is loading
        assertThat(moviesResource.status).isEqualTo(Status.LOADING)
        mainViewModel.getMovies()
        // Verify that repository.getMovies() is called
        verify(repository).getMovies()
        // Verify that the status is success
        moviesResource = mainViewModel.moviesState.getOrAwaitValueTest()
        assertThat(moviesResource.status).isEqualTo(Status.SUCCESS)
        // Verify that the movies have same size with the actual dummy data
        assertThat(moviesResource.data?.size).isEqualTo(actualMoviesSize)
    }

    @Test
    fun `get movies, returns the same elements with the actual dummy data`() = runBlockingTest {
        var moviesResource =
            mainViewModel.moviesState.getOrAwaitValueTest(100, TimeUnit.MILLISECONDS)
        // Verify that the initial status is loading
        assertThat(moviesResource.status).isEqualTo(Status.LOADING)
        mainViewModel.getMovies()
        // Verify that repository.getMovies() is called
        verify(repository).getMovies()
        // Verify that the status is success
        moviesResource = mainViewModel.moviesState.getOrAwaitValueTest()
        assertThat(moviesResource.status).isEqualTo(Status.SUCCESS)
        // Verify that the movies have same elements with the actual dummy data
        assertThat(moviesResource.data).isEqualTo(actualMovies)
    }
    /**
     * -------------------- End of get movies test ------------------------------
     */

    /**
     * -------------------- Get tv shows test ------------------------------
     */
    @Test
    fun `get tv shows, returns not null`() = runBlockingTest {
        var tvShowsResource =
            mainViewModel.tvShowsState.getOrAwaitValueTest(100, TimeUnit.MILLISECONDS)
        // Verify that the initial status is loading
        assertThat(tvShowsResource.status).isEqualTo(Status.LOADING)
        mainViewModel.getTvShows()
        // Verify that repository.getTvShows() is called
        verify(repository).getTvShows()
        // Verify that the status is success
        tvShowsResource = mainViewModel.tvShowsState.getOrAwaitValueTest()
        assertThat(tvShowsResource.status).isEqualTo(Status.SUCCESS)
        // Verify that the tv show data is not null
        assertThat(tvShowsResource.data).isNotNull()
    }

    @Test
    fun `get tv shows, return the list of tv shows`() = runBlockingTest {
        var tvShowsResource =
            mainViewModel.tvShowsState.getOrAwaitValueTest(100, TimeUnit.MILLISECONDS)
        // Verify that the initial status is loading
        assertThat(tvShowsResource.status).isEqualTo(Status.LOADING)
        mainViewModel.getTvShows()
        // Verify that repository.getTvShows() is called
        verify(repository).getTvShows()
        // Verify that the status is success
        tvShowsResource = mainViewModel.tvShowsState.getOrAwaitValueTest()
        assertThat(tvShowsResource.status).isEqualTo(Status.SUCCESS)
        // Verify that the data is the instance of the list of the tv show / not empty
        assertThat(tvShowsResource.data).isNotEmpty()
    }

    @Test
    fun `get tv shows, return the same size with the actual dummy data`() = runBlockingTest {
        var tvShowsResource =
            mainViewModel.tvShowsState.getOrAwaitValueTest(100, TimeUnit.MILLISECONDS)
        // Verify that the initial status is loading
        assertThat(tvShowsResource.status).isEqualTo(Status.LOADING)
        mainViewModel.getTvShows()
        // Verify that repository.getTvShows() is called
        verify(repository).getTvShows()
        // Verify that the status is success
        tvShowsResource = mainViewModel.tvShowsState.getOrAwaitValueTest()
        assertThat(tvShowsResource.status).isEqualTo(Status.SUCCESS)
        // Verify that the tv show have same size with the actual dummy data
        assertThat(tvShowsResource.data?.size).isEqualTo(actualTvShowsSize)
    }

    @Test
    fun `get tv shows, returns the same elements with the actual dummy data`() = runBlockingTest {
        var tvShowsResource =
            mainViewModel.tvShowsState.getOrAwaitValueTest(100, TimeUnit.MILLISECONDS)
        // Verify that the initial status is loading
        assertThat(tvShowsResource.status).isEqualTo(Status.LOADING)
        mainViewModel.getTvShows()
        // Verify that repository.getTvShows() is called
        verify(repository).getTvShows()
        // Verify that the status is success
        tvShowsResource = mainViewModel.tvShowsState.getOrAwaitValueTest()
        assertThat(tvShowsResource.status).isEqualTo(Status.SUCCESS)
        // Verify that the tv show have same elements with the actual dummy data
        assertThat(tvShowsResource.data).isEqualTo(actualTvShows)
    }
    /**
     * -------------------- End of get tv shows test ------------------------------
     */

    /**
     * -------------------- Get trending test ------------------------------
     */
    @Test
    fun `get trending, returns not null`() = runBlockingTest {
        var moviesResource =
            mainViewModel.trendingState.getOrAwaitValueTest(100, TimeUnit.MILLISECONDS)
        // Verify that the initial status is loading
        assertThat(moviesResource.status).isEqualTo(Status.LOADING)
        mainViewModel.getTrending()
        // Verify that repository.getTrending() is called
        verify(repository).getTrending()
        // Verify that the status is success
        moviesResource = mainViewModel.trendingState.getOrAwaitValueTest()
        assertThat(moviesResource.status).isEqualTo(Status.SUCCESS)
        // Verify that the movies data is not null
        assertThat(moviesResource.data).isNotNull()
    }

    @Test
    fun `get trending, return the list of movies`() = runBlockingTest {
        var moviesResource =
            mainViewModel.trendingState.getOrAwaitValueTest(100, TimeUnit.MILLISECONDS)
        // Verify that the initial status is loading
        assertThat(moviesResource.status).isEqualTo(Status.LOADING)
        mainViewModel.getTrending()
        // Verify that repository.getTrending() is called
        verify(repository).getTrending()
        // Verify that the status is success
        moviesResource = mainViewModel.trendingState.getOrAwaitValueTest()
        assertThat(moviesResource.status).isEqualTo(Status.SUCCESS)
        // Verify that the data is the instance of the list of movie / not empty
        assertThat(moviesResource.data).isNotEmpty()
    }

    @Test
    fun `get trending, return the same size with the actual dummy data`() = runBlockingTest {
        var moviesResource =
            mainViewModel.trendingState.getOrAwaitValueTest(100, TimeUnit.MILLISECONDS)
        // Verify that the initial status is loading
        assertThat(moviesResource.status).isEqualTo(Status.LOADING)
        mainViewModel.getTrending()
        // Verify that repository.getTrending() is called
        verify(repository).getTrending()
        // Verify that the status is success
        moviesResource = mainViewModel.trendingState.getOrAwaitValueTest()
        assertThat(moviesResource.status).isEqualTo(Status.SUCCESS)
        // Verify that the movies have same size with the actual dummy data
        assertThat(moviesResource.data?.size).isEqualTo(actualMoviesSize)
    }

    @Test
    fun `get trending, returns the same elements with the actual dummy data`() = runBlockingTest {
        var moviesResource =
            mainViewModel.trendingState.getOrAwaitValueTest(100, TimeUnit.MILLISECONDS)
        // Verify that the initial status is loading
        assertThat(moviesResource.status).isEqualTo(Status.LOADING)
        mainViewModel.getTrending()
        // Verify that repository.getTrending() is called
        verify(repository).getTrending()
        // Verify that the status is success
        moviesResource = mainViewModel.trendingState.getOrAwaitValueTest()
        assertThat(moviesResource.status).isEqualTo(Status.SUCCESS)
        // Verify that the movies have same elements with the actual dummy data
        assertThat(moviesResource.data).isEqualTo(actualMovies)
    }
    /**
     * -------------------- End of get trending test ------------------------------
     */
}