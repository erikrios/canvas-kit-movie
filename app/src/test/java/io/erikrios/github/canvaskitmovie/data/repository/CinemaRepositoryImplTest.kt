package io.erikrios.github.canvaskitmovie.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth
import io.erikrios.github.canvaskitmovie.MainCoroutineRule
import io.erikrios.github.canvaskitmovie.data.model.Movie
import io.erikrios.github.canvaskitmovie.data.model.TvShow
import io.erikrios.github.canvaskitmovie.utils.DummyData
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class CinemaRepositoryImplTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    /**
     * We use this rule for the next submission, when the Coroutine is needed
     */
    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    private lateinit var repository: CinemaRepository
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
        repository = CinemaRepositoryImpl()
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
     * -------------------- Get movies test ------------------------------
     */
    @Test
    fun `get movies, returns not null`() {
        val movies = repository.getMovies()
        Truth.assertThat(movies).isNotNull()
    }

    @Test
    fun `get movies, return the list of movies`() {
        val movies = repository.getMovies()
        Truth.assertThat(movies).isNotEmpty()
    }

    @Test
    fun `get movies, return the same size with the actual dummy data`() {
        val movies = repository.getMovies()
        Truth.assertThat(movies.size).isEqualTo(actualMoviesSize)
    }

    @Test
    fun `get movies, returns the same elements with the actual dummy data`() {
        val movies = repository.getMovies()
        Truth.assertThat(movies).isEqualTo(actualMovies)
    }

    /**
     * -------------------- End of get movies test ------------------------------
     */

    /**
     * -------------------- Get tv shows test ------------------------------
     */
    @Test
    fun `get tv shows, returns not null`() {
        val tvShows = repository.getTvShows()
        Truth.assertThat(tvShows).isNotNull()
    }

    @Test
    fun `get tv shows, return the list of tv show`() {
        val tvShows = repository.getTvShows()
        Truth.assertThat(tvShows).isNotEmpty()
    }

    @Test
    fun `tv shows, return the same size with the actual dummy data`() {
        val tvShows = repository.getTvShows()
        Truth.assertThat(tvShows.size).isEqualTo(actualTvShowsSize)
    }

    @Test
    fun `get tv shows, returns the same elements with the actual dummy data`() {
        val tvShows = repository.getTvShows()
        Truth.assertThat(tvShows).isEqualTo(actualTvShows)
    }

    /**
     * -------------------- End of get tv shows test ------------------------------
     */

    /**
     * -------------------- Get movie by id test ------------------------------
     */
    @Test
    fun `get movie with not exist id, returns null`() {
        val movie = repository.getMovieById(notExistMovieId)
        Truth.assertThat(movie).isNull()
    }

    @Test
    fun `get movie with valid id, returns a valid movie`() {
        val movie = repository.getMovieById(randomMovieId)
        Truth.assertThat(movie).isEqualTo(actualDummyMovie)
    }

    /**
     * -------------------- End of get movie by id test ------------------------------
     */

    /**
     * -------------------- Get tv show by id test ------------------------------
     */
    @Test
    fun `get tv show with not exist id, returns null`() {
        val tvShow = repository.getTvShowById(notExistTvShowId)
        Truth.assertThat(tvShow).isNull()
    }

    @Test
    fun `get tv show with valid id, returns a valid tv show`() {
        val tvShow = repository.getTvShowById(randomTvShowId)
        Truth.assertThat(tvShow).isEqualTo(actualDummyTvShow)
    }

    /**
     * -------------------- End of get tv show by id test ------------------------------
     */
}