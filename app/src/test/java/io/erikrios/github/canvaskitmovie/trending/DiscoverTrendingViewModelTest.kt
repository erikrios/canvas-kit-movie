package io.erikrios.github.canvaskitmovie.trending

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
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class DiscoverTrendingViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    @Mock
    private lateinit var trendingUseCase: TrendingUseCase

    private lateinit var discoverTrendingViewModel: DiscoverTrendingViewModel
    private lateinit var actualTrendings: List<Trending>

    @Suppress("DEPRECATION")
    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        actualTrendings = generateTrendings()
        `when`(trendingUseCase.getTrendings()).thenReturn(flow {
            emit(
                Resource.Success(
                    actualTrendings
                )
            )
        })
        discoverTrendingViewModel = DiscoverTrendingViewModel(trendingUseCase)
    }

    @Test
    fun `get trendings return the list of trendings`() {
        val trendingsResource = discoverTrendingViewModel.trendingsState.getOrAwaitValueTest()
        verify(trendingUseCase).getTrendings()
        assertThat(trendingsResource).isInstanceOf(Resource.Success::class.java)
        val trendings = trendingsResource.data
        assertThat(trendings).isNotEmpty()
    }
}