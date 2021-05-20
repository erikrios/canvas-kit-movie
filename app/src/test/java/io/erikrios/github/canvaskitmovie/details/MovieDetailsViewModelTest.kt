package io.erikrios.github.canvaskitmovie.details

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import io.erikrios.github.canvaskitmovie.MainCoroutineRule
import io.erikrios.github.canvaskitmovie.getOrAwaitValueTest
import io.github.erikrios.canvaskitmovie.core.data.Resource
import io.github.erikrios.canvaskitmovie.core.domain.model.Movie
import io.github.erikrios.canvaskitmovie.core.domain.usecase.MovieUseCase
import io.github.erikrios.canvaskitmovie.core.utils.DummyData.generateMovies
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.verify

@ExperimentalCoroutinesApi
class MovieDetailsViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    @Mock
    private lateinit var movieUseCase: MovieUseCase

    private lateinit var movieDetailsViewModel: MovieDetailsViewModel
    private lateinit var actualMovie: Movie

    @Suppress("DEPRECATION")
    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        actualMovie = generateMovies()[0]
        `when`(movieUseCase.getMovie(actualMovie.id)).thenReturn(flow {
            emit(
                Resource.Success(
                    actualMovie
                )
            )
        })
        `when`(
            movieUseCase.setFavoriteMovie(
                actualMovie,
                true
            )
        ).then { true.also { actualMovie.isFavorite = it } }
        movieDetailsViewModel = MovieDetailsViewModel(movieUseCase)
    }

    @Test
    fun `get movie return the movie`() {
        val movieResource = movieDetailsViewModel.getMovie(actualMovie.id).getOrAwaitValueTest()
        verify(movieUseCase).getMovie(actualMovie.id)
        assertThat(movieResource).isInstanceOf(Resource.Success::class.java)
        val movie = movieResource.data
        assertThat(movie).isEqualTo(actualMovie)
    }

    @Test
    fun `set favorite movie, change the isFavorite field to true`() {
        movieDetailsViewModel.setFavoriteMovie(actualMovie, true)
        verify(movieUseCase).setFavoriteMovie(actualMovie, true)
        assertThat(actualMovie.isFavorite).isEqualTo(true)
    }
}