package io.github.erikrios.canvaskitmovie.favorite

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import io.github.erikrios.canvaskitmovie.core.domain.model.Trending
import io.github.erikrios.canvaskitmovie.core.domain.usecase.TrendingUseCase
import io.github.erikrios.canvaskitmovie.core.utils.DummyData.generateTrendings
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
class FavoriteTrendingsViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    @Mock
    private lateinit var trendingUseCase: TrendingUseCase

    private lateinit var favoriteTrendingsViewModel: FavoriteTrendingsViewModel
    private lateinit var actualTrendings: List<Trending>

    @Suppress("DEPRECATION")
    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        actualTrendings = generateTrendings()
        `when`(trendingUseCase.getFavoriteTrendings(any())).thenReturn(flow { emit(actualTrendings) })
        favoriteTrendingsViewModel = FavoriteTrendingsViewModel(trendingUseCase)
    }

    @Test
    fun `get trendings return the list of trendings`() {
        val trendings =
            favoriteTrendingsViewModel.getFavoriteTrendings(SortUtils.Sort.RANDOM)
                .getOrAwaitValueTest()
        verify(trendingUseCase).getFavoriteTrendings(SortUtils.Sort.RANDOM)
        assertThat(trendings).isNotEmpty()
    }
}