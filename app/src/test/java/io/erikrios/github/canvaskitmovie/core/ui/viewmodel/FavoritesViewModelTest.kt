package io.erikrios.github.canvaskitmovie.core.ui.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.DataSource
import com.google.common.truth.Truth.assertThat
import io.erikrios.github.canvaskitmovie.MainCoroutineRule
import io.erikrios.github.canvaskitmovie.core.domain.model.Movie
import io.erikrios.github.canvaskitmovie.core.domain.model.TvShow
import io.erikrios.github.canvaskitmovie.core.domain.repository.ITrendingRepository
import io.erikrios.github.canvaskitmovie.favorites.FavoritesViewModel
import io.erikrios.github.canvaskitmovie.core.utils.DummyData
import io.erikrios.github.canvaskitmovie.core.utils.PagedListUtil
import io.erikrios.github.canvaskitmovie.core.utils.SortUtils
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.any
import org.mockito.kotlin.verify

@ExperimentalCoroutinesApi
class FavoritesViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    @Mock
    private lateinit var repository: ITrendingRepository

    private lateinit var favoritesViewModel: FavoritesViewModel
    private lateinit var actualMovies: List<Movie>
    private lateinit var actualTvShows: List<TvShow>
    private var actualMoviesSize: Int = 0
    private var actualTvShowsSize: Int = 0

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        actualMovies = DummyData.generateMovies()
        actualTvShows = DummyData.generateTvShows()
        actualMoviesSize = actualMovies.size
        actualTvShowsSize = actualTvShows.size

        runBlockingTest {
            @SuppressWarnings("unchecked")
            val movieDataSourceFactory =
                Mockito.mock(DataSource.Factory::class.java) as DataSource.Factory<Int, Movie>
            `when`(repository.getFavoriteMovies(any())).thenReturn(movieDataSourceFactory)

            val tvShowDataSourceFactory =
                Mockito.mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvShow>
            `when`(repository.getFavoriteTvShows(any())).thenReturn(tvShowDataSourceFactory)
        }

        favoritesViewModel = FavoritesViewModel(repository)
    }

    @Test
    fun `get favorite movies, return the list of movie`() {
        favoritesViewModel.getFavoriteMovies(SortUtils.Sort.RANDOM)
        val movies = PagedListUtil.mockPagedList(actualMovies)
        verify(repository).getFavoriteMovies(SortUtils.Sort.RANDOM)
        assertThat(movies).isNotNull()
        assertThat(movies.size).isEqualTo(actualMovies.size)
    }

    @Test
    fun `get favorite tv shows, return the list of tv show`() {
        favoritesViewModel.getFavoriteTvShows(SortUtils.Sort.RANDOM)
        val tvShows = PagedListUtil.mockPagedList(actualTvShows)
        verify(repository).getFavoriteTvShows(SortUtils.Sort.RANDOM)
        assertThat(tvShows).isNotNull()
        assertThat(tvShows.size).isEqualTo(actualTvShows.size)
    }
}