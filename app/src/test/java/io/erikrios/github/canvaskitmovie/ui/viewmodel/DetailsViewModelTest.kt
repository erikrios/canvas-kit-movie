package io.erikrios.github.canvaskitmovie.ui.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import io.erikrios.github.canvaskitmovie.MainCoroutineRule
import io.erikrios.github.canvaskitmovie.data.model.Movie
import io.erikrios.github.canvaskitmovie.data.model.TvShow
import io.erikrios.github.canvaskitmovie.data.repository.CinemaRepositoryImpl
import io.erikrios.github.canvaskitmovie.getOrAwaitValueTest
import io.erikrios.github.canvaskitmovie.utils.DummyData
import io.erikrios.github.canvaskitmovie.utils.Resource
import io.erikrios.github.canvaskitmovie.utils.Status
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.verify
import java.util.concurrent.TimeUnit

@ExperimentalCoroutinesApi
class DetailsViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    @Mock
    private lateinit var repository: CinemaRepositoryImpl

    private lateinit var detailsViewModel: DetailsViewModel
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

        runBlockingTest {
            Mockito.`when`(repository.getMovies()).thenReturn(Resource.success(actualMovies))
            Mockito.`when`(repository.getTvShows()).thenReturn(Resource.success(actualTvShows))
            Mockito.`when`(repository.getMovieById(randomMovieId)).thenReturn(
                Resource.success(
                    actualDummyMovie
                )
            )
            Mockito.`when`(repository.getTvShowById(randomTvShowId)).thenReturn(
                Resource.success(
                    actualDummyTvShow
                )
            )
            Mockito.`when`(repository.getMovieById(notExistMovieId)).thenReturn(
                Resource.error(
                    "Internal server error.", null
                )
            )
            Mockito.`when`(repository.getTvShowById(notExistTvShowId)).thenReturn(
                Resource.error(
                    "Internal server error.", null
                )
            )
        }

        detailsViewModel = DetailsViewModel(repository)
    }

    /**
     * -------------------- Get movie by id test ------------------------------
     */
    @Test
    fun `get movie with not exist id, returns error status`() = runBlockingTest {
        var movieResource =
            detailsViewModel.movieState.getOrAwaitValueTest(100, TimeUnit.MILLISECONDS)
        // Verify that the initial status is loading
        assertThat(movieResource.status).isEqualTo(Status.LOADING)
        detailsViewModel.getMovieById(notExistMovieId)
        // Verify that repository.getMovieById() is called
        verify(repository).getMovieById(notExistMovieId)
        // Verify that the status is error
        movieResource = detailsViewModel.movieState.getOrAwaitValueTest()
        assertThat(movieResource.status).isEqualTo(Status.ERROR)
        // Verify that the movie data is null
        assertThat(movieResource.data).isNull()
    }

    @Test
    fun `get movie with valid id, returns a valid movie`() = runBlockingTest {
        var movieResource =
            detailsViewModel.movieState.getOrAwaitValueTest(100, TimeUnit.MILLISECONDS)
        // Verify that the initial status is loading
        assertThat(movieResource.status).isEqualTo(Status.LOADING)
        detailsViewModel.getMovieById(randomMovieId)
        // Verify that repository.getMovieById() is called
        verify(repository).getMovieById(randomMovieId)
        // Verify that the status is success
        movieResource = detailsViewModel.movieState.getOrAwaitValueTest()
        assertThat(movieResource.status).isEqualTo(Status.SUCCESS)
        // Verify that the movie data is equals to actual dummy movie
        assertThat(movieResource.data).isEqualTo(actualDummyMovie)
    }

    /**
     * -------------------- End of get movie by id test ------------------------------
     */

    /**
     * -------------------- Get tv show by id test ------------------------------
     */
    @Test
    fun `get tv show with not exist id, returns null`() = runBlockingTest {
        var tvShowResource =
            detailsViewModel.tvShowState.getOrAwaitValueTest(100, TimeUnit.MILLISECONDS)
        // Verify that the initial status is loading
        assertThat(tvShowResource.status).isEqualTo(Status.LOADING)
        detailsViewModel.getTvShowById(notExistTvShowId)
        // Verify that repository.getTvShowById() is called
        verify(repository).getTvShowById(notExistTvShowId)
        // Verify that the status is error
        tvShowResource = detailsViewModel.tvShowState.getOrAwaitValueTest()
        assertThat(tvShowResource.status).isEqualTo(Status.ERROR)
        // Verify that the tv show data is null
        assertThat(tvShowResource.data).isNull()
    }

    @Test
    fun `get tv show with valid id, returns a valid tv show`() = runBlockingTest {
        var tvShowResource =
            detailsViewModel.tvShowState.getOrAwaitValueTest(100, TimeUnit.MILLISECONDS)
        // Verify that the initial status is loading
        assertThat(tvShowResource.status).isEqualTo(Status.LOADING)
        detailsViewModel.getTvShowById(randomTvShowId)
        // Verify that repository.getTvShowById() is called
        verify(repository).getTvShowById(randomTvShowId)
        // Verify that the status is success
        tvShowResource = detailsViewModel.tvShowState.getOrAwaitValueTest()
        assertThat(tvShowResource.status).isEqualTo(Status.SUCCESS)
        // Verify that the tv show data is equals to actual dummy tv show
        assertThat(tvShowResource.data).isEqualTo(actualDummyTvShow)
    }

    /**
     * -------------------- End of get tv show by id test ------------------------------
     */
}