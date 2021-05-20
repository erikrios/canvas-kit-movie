package io.erikrios.github.canvaskitmovie.details

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import io.erikrios.github.canvaskitmovie.MainCoroutineRule
import io.erikrios.github.canvaskitmovie.getOrAwaitValueTest
import io.github.erikrios.canvaskitmovie.core.data.Resource
import io.github.erikrios.canvaskitmovie.core.domain.model.Trending
import io.github.erikrios.canvaskitmovie.core.domain.usecase.TrendingUseCase
import io.github.erikrios.canvaskitmovie.core.utils.DummyData.generateTrendings
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
class TrendingDetailsViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    @Mock
    private lateinit var trendingUseCase: TrendingUseCase

    private lateinit var trendingDetailsViewModel: TrendingDetailsViewModel
    private lateinit var actualTrending: Trending

    @Suppress("DEPRECATION")
    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        actualTrending = generateTrendings()[0]
        `when`(trendingUseCase.getTrending(actualTrending.id)).thenReturn(flow {
            emit(
                Resource.Success(
                    actualTrending
                )
            )
        })
        `when`(
            trendingUseCase.setFavoriteTrending(
                actualTrending,
                true
            )
        ).then { true.also { actualTrending.isFavorite = it } }
        trendingDetailsViewModel = TrendingDetailsViewModel(trendingUseCase)
    }

    @Test
    fun `get trending return the trending`() {
        val trendingResource =
            trendingDetailsViewModel.getTrending(actualTrending.id).getOrAwaitValueTest()
        verify(trendingUseCase).getTrending(actualTrending.id)
        assertThat(trendingResource).isInstanceOf(Resource.Success::class.java)
        val trending = trendingResource.data
        assertThat(trending).isEqualTo(actualTrending)
    }

    @Test
    fun `set favorite trending, change the isFavorite field to true`() {
        trendingDetailsViewModel.setFavoriteTrending(actualTrending, true)
        verify(trendingUseCase).setFavoriteTrending(actualTrending, true)
        assertThat(actualTrending.isFavorite).isEqualTo(true)
    }
}