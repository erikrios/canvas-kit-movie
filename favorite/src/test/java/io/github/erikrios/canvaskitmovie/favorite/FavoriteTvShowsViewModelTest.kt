package io.github.erikrios.canvaskitmovie.favorite

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import io.github.erikrios.canvaskitmovie.core.domain.model.TvShow
import io.github.erikrios.canvaskitmovie.core.domain.usecase.TvShowUseCase
import io.github.erikrios.canvaskitmovie.core.utils.DummyData.generateTvShows
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
class FavoriteTvShowsViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    @Mock
    private lateinit var tvShowUseCase: TvShowUseCase

    private lateinit var favoriteTvShowsViewModel: FavoriteTvShowsViewModel
    private lateinit var actualTvShows: List<TvShow>

    @Suppress("DEPRECATION")
    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        actualTvShows = generateTvShows()
        `when`(tvShowUseCase.getFavoriteTvShows(any())).thenReturn(flow { emit(actualTvShows) })
        favoriteTvShowsViewModel = FavoriteTvShowsViewModel(tvShowUseCase)
    }

    @Test
    fun `get tv shows return the list of tv shows`() {
        val tvShows =
            favoriteTvShowsViewModel.getFavoriteTvShows(SortUtils.Sort.RANDOM).getOrAwaitValueTest()
        verify(tvShowUseCase).getFavoriteTvShows(SortUtils.Sort.RANDOM)
        assertThat(tvShows).isNotEmpty()
    }
}