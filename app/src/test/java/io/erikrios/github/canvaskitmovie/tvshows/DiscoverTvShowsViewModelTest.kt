package io.erikrios.github.canvaskitmovie.tvshows

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import io.erikrios.github.canvaskitmovie.MainCoroutineRule
import io.erikrios.github.canvaskitmovie.getOrAwaitValueTest
import io.github.erikrios.canvaskitmovie.core.data.Resource
import io.github.erikrios.canvaskitmovie.core.domain.model.TvShow
import io.github.erikrios.canvaskitmovie.core.domain.usecase.TvShowUseCase
import io.github.erikrios.canvaskitmovie.core.utils.DummyData.generateTvShows
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class DiscoverTvShowsViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    @Mock
    private lateinit var tvShowUseCase: TvShowUseCase

    private lateinit var discoverTvShowsViewModel: DiscoverTvShowsViewModel
    private lateinit var actualTvShows: List<TvShow>

    @Suppress("DEPRECATION")
    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        actualTvShows = generateTvShows()
        `when`(tvShowUseCase.getTvShows()).thenReturn(flow { emit(Resource.Success(actualTvShows)) })
        discoverTvShowsViewModel = DiscoverTvShowsViewModel(tvShowUseCase)
    }

    @Test
    fun `get tv shows return the list of tv shows`() {
        val tvShowsResource = discoverTvShowsViewModel.tvShowsState.getOrAwaitValueTest()
        assertThat(tvShowsResource).isInstanceOf(Resource.Success::class.java)
        val tvShows = tvShowsResource.data
        assertThat(tvShows).isNotEmpty()
    }
}