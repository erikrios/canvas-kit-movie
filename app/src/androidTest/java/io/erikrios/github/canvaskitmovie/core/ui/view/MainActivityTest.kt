package io.erikrios.github.canvaskitmovie.core.ui.view

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import io.erikrios.github.canvaskitmovie.MainActivity
import io.erikrios.github.canvaskitmovie.R
import io.erikrios.github.canvaskitmovie.core.domain.model.Movie
import io.erikrios.github.canvaskitmovie.core.domain.model.TvShow
import io.erikrios.github.canvaskitmovie.core.utils.DummyData.generateMovies
import io.erikrios.github.canvaskitmovie.core.utils.DummyData.generateTvShows
import io.erikrios.github.canvaskitmovie.core.utils.EspressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @get:Rule
    var activityRule = ActivityScenarioRule(MainActivity::class.java)

    private lateinit var dummyMovies: List<Movie>
    private lateinit var dummyTvShows: List<TvShow>
    private var dummyMovieIndex: Int = 0
    private var dummyTvShowIndex: Int = 0
    private lateinit var dummyMovie: Movie
    private lateinit var dummyTvShow: TvShow

    @Before
    fun setUp() {
        ActivityScenario.launch(MainActivity::class.java)
        dummyMovies = generateMovies()
        dummyTvShows = generateTvShows()
        dummyMovieIndex = (dummyMovies.indices).random()
        dummyTvShowIndex = (dummyTvShows.indices).random()
        dummyMovie = dummyMovies[dummyMovieIndex]
        dummyTvShow = dummyTvShows[dummyTvShowIndex]
        IdlingRegistry.getInstance().register(EspressoIdlingResource.idlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.idlingResource)
    }

    @Test
    fun loadMovies() {
        onView(withId(R.id.rv_movies)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movies)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                dummyMovies.size
            )
        )
    }

    @Test
    fun loadTvShows() {
        onView(withId(R.id.item_tv_shows)).perform(click())
        onView(withId(R.id.rv_tv_shows)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tv_shows)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                dummyTvShows.size
            )
        )
    }

    @Test
    fun loadTrending() {
        onView(withId(R.id.item_trending)).perform(click())
        onView(withId(R.id.rv_trending)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_trending)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                dummyTvShows.size
            )
        )
    }

    @Test
    fun loadMovieDetails() {
        onView(withId(R.id.rv_movies)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movies))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyMovieIndex))
        onView(withId(R.id.rv_movies))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    dummyMovieIndex,
                    click()
                )
            )

        onView(withId(R.id.img_backdrop)).check(matches(isDisplayed()))
        onView(withId(R.id.img_poster)).check(matches(isDisplayed()))
        onView(withId(R.id.toolbar)).check(matches(isDisplayed()))
        onView(withId(R.id.fab_favorite)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_title)).check(matches(isDisplayed()))
        onView(withId(R.id.img_backdrop)).perform(swipeUp())
        onView(withId(R.id.tv_rating_info)).check(matches(isDisplayed()))
        onView(withId(R.id.rb_vote_average)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_vote_info)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_status_info)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_popularity_info)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_release_date_info)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_overview)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_genres)).check(matches(isDisplayed()))
    }

    @Test
    fun loadTvShowDetails() {
        onView(withId(R.id.item_tv_shows)).perform(click())
        onView(withId(R.id.rv_tv_shows)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tv_shows))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyTvShowIndex))
        onView(withId(R.id.rv_tv_shows))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    dummyTvShowIndex,
                    click()
                )
            )

        onView(withId(R.id.img_backdrop)).check(matches(isDisplayed()))
        onView(withId(R.id.img_poster)).check(matches(isDisplayed()))
        onView(withId(R.id.toolbar)).check(matches(isDisplayed()))
        onView(withId(R.id.fab_favorite)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_genres)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_name)).check(matches(isDisplayed()))
        onView(withId(R.id.img_backdrop)).perform(swipeUp())
        onView(withId(R.id.tv_rating_info)).check(matches(isDisplayed()))
        onView(withId(R.id.rb_vote_average)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_vote_info)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_status_info)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_popularity_info)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_first_air_date_info)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_overview)).perform(swipeUp())
        onView(withId(R.id.tv_overview)).check(matches(isDisplayed()))
        try {
            onView(withId(R.id.rv_creators)).check(matches(isDisplayed()))
        } catch (e: Exception) {
            print("The creators data is null or empty")
        }
    }

    @Test
    fun loadTrendingDetails() {
        onView(withId(R.id.item_trending)).perform(click())
        onView(withId(R.id.rv_trending)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_trending))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyMovieIndex))
        onView(withId(R.id.rv_trending))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    dummyMovieIndex,
                    click()
                )
            )

        onView(withId(R.id.img_backdrop)).check(matches(isDisplayed()))
        onView(withId(R.id.img_poster)).check(matches(isDisplayed()))
        onView(withId(R.id.toolbar)).check(matches(isDisplayed()))
        onView(withId(R.id.fab_favorite)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_title)).check(matches(isDisplayed()))
        onView(withId(R.id.img_backdrop)).perform(swipeUp())
        onView(withId(R.id.tv_rating_info)).check(matches(isDisplayed()))
        onView(withId(R.id.rb_vote_average)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_vote_info)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_status_info)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_popularity_info)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_release_date_info)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_overview)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_genres)).check(matches(isDisplayed()))
    }

    @Test
    fun loadFavoriteMovies() {
        onView(withId(R.id.rv_movies)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movies))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyMovieIndex))
        onView(withId(R.id.rv_movies))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    dummyMovieIndex,
                    click()
                )
            )
        onView(withId(R.id.fab_favorite)).perform(click())
        onView(isRoot()).perform(pressBack())
        onView(withId(R.id.item_favorites)).perform(click())
        onView(withId(R.id.rv_favorite_movies)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_favorite_movies)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )

        onView(withId(R.id.fab_favorite)).perform(click())

        onView(withId(R.id.img_backdrop)).check(matches(isDisplayed()))
        onView(withId(R.id.img_poster)).check(matches(isDisplayed()))
        onView(withId(R.id.toolbar)).check(matches(isDisplayed()))
        onView(withId(R.id.fab_favorite)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_title)).check(matches(isDisplayed()))
        onView(withId(R.id.img_backdrop)).perform(swipeUp())
        onView(withId(R.id.tv_rating_info)).check(matches(isDisplayed()))
        onView(withId(R.id.rb_vote_average)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_vote_info)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_status_info)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_popularity_info)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_release_date_info)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_overview)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_genres)).check(matches(isDisplayed()))

        onView(isRoot()).perform(pressBack())
    }

    @Test
    fun loadFavoriteTvShows() {
        onView(withId(R.id.item_tv_shows)).perform(click())
        onView(withId(R.id.rv_tv_shows)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tv_shows))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyTvShowIndex))
        onView(withId(R.id.rv_tv_shows))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    dummyTvShowIndex,
                    click()
                )
            )
        onView(withId(R.id.fab_favorite)).perform(click())
        onView(isRoot()).perform(pressBack())
        onView(withId(R.id.item_favorites)).perform(click())
        onView(withId(R.id.rv_favorite_tv_shows)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_favorite_tv_shows)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )

        onView(withId(R.id.fab_favorite)).perform(click())

        onView(withId(R.id.img_backdrop)).check(matches(isDisplayed()))
        onView(withId(R.id.img_poster)).check(matches(isDisplayed()))
        onView(withId(R.id.toolbar)).check(matches(isDisplayed()))
        onView(withId(R.id.fab_favorite)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_genres)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_name)).check(matches(isDisplayed()))
        onView(withId(R.id.img_backdrop)).perform(swipeUp())
        onView(withId(R.id.tv_rating_info)).check(matches(isDisplayed()))
        onView(withId(R.id.rb_vote_average)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_vote_info)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_status_info)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_popularity_info)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_first_air_date_info)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_overview)).perform(swipeUp())
        onView(withId(R.id.tv_overview)).check(matches(isDisplayed()))
        try {
            onView(withId(R.id.rv_creators)).check(matches(isDisplayed()))
        } catch (e: Exception) {
            print("The creators data is null or empty")
        }

        onView(isRoot()).perform(pressBack())
    }
}