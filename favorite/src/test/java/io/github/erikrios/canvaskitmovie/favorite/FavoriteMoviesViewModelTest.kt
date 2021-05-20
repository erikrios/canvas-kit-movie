package io.github.erikrios.canvaskitmovie.favorite

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import io.github.erikrios.canvaskitmovie.core.domain.model.Movie
import io.github.erikrios.canvaskitmovie.core.domain.usecase.MovieUseCase
import io.github.erikrios.canvaskitmovie.core.utils.DummyData.generateMovies
import io.github.erikrios.canvaskitmovie.core.utils.SortUtils
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.any

@ExperimentalCoroutinesApi
class FavoriteMoviesViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    @Mock
    private lateinit var movieUseCase: MovieUseCase

    private lateinit var favoriteMoviesViewModel: FavoriteMoviesViewModel
    private lateinit var actualMovies: List<Movie>

    @Suppress("DEPRECATION")
    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        actualMovies = generateMovies()
        `when`(movieUseCase.getFavoriteMovies(any())).thenReturn(flow { emit(actualMovies) })
        favoriteMoviesViewModel = FavoriteMoviesViewModel(movieUseCase)
    }

    @Test
    fun `get movies return the list of movies`() {
        val movies =
            favoriteMoviesViewModel.getFavoriteMovies(SortUtils.Sort.RANDOM).getOrAwaitValueTest()
        verify(movieUseCase).getFavoriteMovies(SortUtils.Sort.RANDOM)
        assertThat(movies).isNotEmpty()
    }
}