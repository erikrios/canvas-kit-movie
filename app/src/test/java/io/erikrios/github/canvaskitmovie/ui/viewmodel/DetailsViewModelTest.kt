package io.erikrios.github.canvaskitmovie.ui.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import io.erikrios.github.canvaskitmovie.MainCoroutineRule
import io.erikrios.github.canvaskitmovie.data.model.Movie
import io.erikrios.github.canvaskitmovie.data.model.TvShow
import io.erikrios.github.canvaskitmovie.data.repository.CinemaRepositoryImpl
import io.erikrios.github.canvaskitmovie.getOrAwaitValueTest
import io.erikrios.github.canvaskitmovie.utils.DummyData
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class DetailsViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    /**
     * We use this rule for the next submission, when the Coroutine is needed
     */
    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

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
        detailsViewModel = DetailsViewModel(CinemaRepositoryImpl())
        actualMovies = DummyData.generateMovies()
        actualTvShows = DummyData.generateTvShows()
        actualMoviesSize = actualMovies.size
        actualTvShowsSize = actualTvShows.size
        randomMovieId = actualMovies.random().id
        randomTvShowId = actualTvShows.random().id
        actualDummyMovie = DummyData.getMovieById(randomMovieId)
        actualDummyTvShow = DummyData.getTvShowById(randomTvShowId)
    }

    /**
     * -------------------- Get movie by id test ------------------------------
     */
    @Test
    fun `get movie with not exist id, returns null`() {
        detailsViewModel.getMovieById(notExistMovieId)
        val movie = detailsViewModel.movieState.getOrAwaitValueTest()
        assertThat(movie).isNull()
    }

    @Test
    fun `get movie with valid id, returns a valid movie`() {
        detailsViewModel.getMovieById(randomMovieId)
        val movie = detailsViewModel.movieState.getOrAwaitValueTest()
        assertThat(movie).isEqualTo(actualDummyMovie)
    }

    /**
     * -------------------- End of get movie by id test ------------------------------
     */

    /**
     * -------------------- Get tv show by id test ------------------------------
     */
    @Test
    fun `get tv show with not exist id, returns null`() {
        detailsViewModel.getTvShowById(notExistTvShowId)
        val tvShow = detailsViewModel.tvShowState.getOrAwaitValueTest()
        assertThat(tvShow).isNull()
    }

    @Test
    fun `get tv show with valid id, returns a valid tv show`() {
        detailsViewModel.getTvShowById(randomTvShowId)
        val tvShow = detailsViewModel.tvShowState.getOrAwaitValueTest()
        assertThat(tvShow).isEqualTo(actualDummyTvShow)
    }

    /**
     * -------------------- End of get tv show by id test ------------------------------
     */
}