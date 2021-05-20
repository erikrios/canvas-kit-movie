package io.erikrios.github.canvaskitmovie.details

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
import org.mockito.kotlin.verify

@ExperimentalCoroutinesApi
class TvShowDetailsViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    @Mock
    private lateinit var tvShowUseCase: TvShowUseCase

    private lateinit var tvShowDetailsViewModel: TvShowDetailsViewModel
    private lateinit var actualTvShow: TvShow

    @Suppress("DEPRECATION")
    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        actualTvShow = generateTvShows()[0]
        `when`(tvShowUseCase.getTvShow(actualTvShow.id)).thenReturn(flow {
            emit(
                Resource.Success(
                    actualTvShow
                )
            )
        })
        `when`(
            tvShowUseCase.setFavoriteTvShow(
                actualTvShow,
                true
            )
        ).then { true.also { actualTvShow.isFavorite = it } }
        tvShowDetailsViewModel = TvShowDetailsViewModel(tvShowUseCase)
    }

    @Test
    fun `get tv show return the tv show`() {
        val tvShowResource = tvShowDetailsViewModel.getTvShow(actualTvShow.id).getOrAwaitValueTest()
        verify(tvShowUseCase).getTvShow(actualTvShow.id)
        assertThat(tvShowResource).isInstanceOf(Resource.Success::class.java)
        val tvShow = tvShowResource.data
        assertThat(tvShow).isEqualTo(actualTvShow)
    }

    @Test
    fun `set favorite tv show, change the isFavorite field to true`() {
        tvShowDetailsViewModel.setFavoriteTvShow(actualTvShow, true)
        verify(tvShowUseCase).setFavoriteTvShow(actualTvShow, true)
        assertThat(actualTvShow.isFavorite).isEqualTo(true)
    }
}