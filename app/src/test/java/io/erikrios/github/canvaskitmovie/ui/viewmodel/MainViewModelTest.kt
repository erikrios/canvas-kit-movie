package io.erikrios.github.canvaskitmovie.ui.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import io.erikrios.github.canvaskitmovie.MainCoroutineRule
import io.erikrios.github.canvaskitmovie.data.model.Movie
import io.erikrios.github.canvaskitmovie.data.model.TvShow
import io.erikrios.github.canvaskitmovie.data.repository.CinemaRepositoryImpl
import io.erikrios.github.canvaskitmovie.getOrAwaitValueTest
import io.erikrios.github.canvaskitmovie.utils.DummyData.generateMovies
import io.erikrios.github.canvaskitmovie.utils.DummyData.generateTvShows
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class MainViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    /**
     * We use this rule for the next submission, when the Coroutine is needed
     */
    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    private lateinit var mainViewModel: MainViewModel
    private lateinit var actualMovies: List<Movie>
    private lateinit var actualTvShows: List<TvShow>
    private var actualMoviesSize: Int = 0
    private var actualTvShowsSize: Int = 0
    private var randomMovieIndex = 0
    private var randomTvShowIndex = 0
    private lateinit var actualDummyMovie: Movie
    private lateinit var actualDummyTvShow: TvShow

    @Before
    fun setUp() {
        mainViewModel = MainViewModel(CinemaRepositoryImpl())
        actualMovies = generateMovies()
        actualTvShows = generateTvShows()
        actualMoviesSize = actualMovies.size
        actualTvShowsSize = actualTvShows.size
        randomMovieIndex = (0 until actualMoviesSize).random()
        randomTvShowIndex = (0 until actualTvShowsSize).random()
        actualDummyMovie = actualMovies[randomMovieIndex]
        actualDummyTvShow = actualTvShows[randomTvShowIndex]
    }

    @Test
    fun `get movies, returns not null`() {
        mainViewModel.getMovies()
        val movies = mainViewModel.moviesState.getOrAwaitValueTest()
        assertThat(movies).isNotNull()
    }

    @Test
    fun `get movies, return the list of movies`() {
        mainViewModel.getMovies()
        val movies = mainViewModel.moviesState.getOrAwaitValueTest()
        assertThat(movies).isNotEmpty()
    }

    @Test
    fun `get movies, return the same size with the actual dummy data`() {
        mainViewModel.getMovies()
        val movies = mainViewModel.moviesState.getOrAwaitValueTest()
        assertThat(movies.size).isEqualTo(actualMoviesSize)
    }

    @Test
    fun `get movies, returns the same elements with the actual dummy data`() {
        mainViewModel.getMovies()
        val movies = mainViewModel.moviesState.getOrAwaitValueTest()
        assertThat(movies).isEqualTo(actualMovies)
    }

    @Test
    fun `get tv shows, returns not null`() {
        mainViewModel.getTvShows()
        val tvShows = mainViewModel.tvShowsState.getOrAwaitValueTest()
        assertThat(tvShows).isNotNull()
    }

    @Test
    fun `get tv shows, return the list of tv show`() {
        mainViewModel.getTvShows()
        val tvShows = mainViewModel.tvShowsState.getOrAwaitValueTest()
        assertThat(tvShows).isNotEmpty()
    }

    @Test
    fun `tv shows, return the same size with the actual dummy data`() {
        mainViewModel.getTvShows()
        val tvShows = mainViewModel.tvShowsState.getOrAwaitValueTest()
        assertThat(tvShows.size).isEqualTo(actualTvShowsSize)
    }

    @Test
    fun `get tv shows, returns the same elements with the actual dummy data`() {
        mainViewModel.getTvShows()
        val tvShows = mainViewModel.tvShowsState.getOrAwaitValueTest()
        assertThat(tvShows).isEqualTo(actualTvShows)
    }
}