package io.erikrios.github.canvaskitmovie.movies

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
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class DiscoverMoviesViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    @Mock
    private lateinit var movieUseCase: MovieUseCase

    private lateinit var discoverMoviesViewModel: DiscoverMoviesViewModel
    private lateinit var actualMovies: List<Movie>

    @Suppress("DEPRECATION")
    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        actualMovies = generateMovies()
        `when`(movieUseCase.getMovies()).thenReturn(flow { emit(Resource.Success(actualMovies)) })
        discoverMoviesViewModel = DiscoverMoviesViewModel(movieUseCase)
    }

    @Test
    fun `get movies return the list of movies`() {
        val moviesResource = discoverMoviesViewModel.moviesState.getOrAwaitValueTest()
        verify(movieUseCase).getMovies()
        assertThat(moviesResource).isInstanceOf(Resource.Success::class.java)
        val movies = moviesResource.data
        assertThat(movies).isNotEmpty()
    }
}