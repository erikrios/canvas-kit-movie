package io.erikrios.github.canvaskitmovie.ui.view

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.swipeUp
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import io.erikrios.github.canvaskitmovie.R
import io.erikrios.github.canvaskitmovie.data.model.Movie
import io.erikrios.github.canvaskitmovie.data.model.TvShow
import io.erikrios.github.canvaskitmovie.utils.DummyData.generateMovies
import io.erikrios.github.canvaskitmovie.utils.DummyData.generateTvShows
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
        onView(withId(R.id.fab_share)).check(matches(isDisplayed()))
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

        onView(withId(R.id.tv_title)).check(matches(withText(dummyMovie.title)))
        onView(withId(R.id.tv_rating_info)).check(
            matches(
                withText(
                    String.format(
                        "%.1f",
                        dummyMovie.voteAverage
                    )
                )
            )
        )
        onView(withId(R.id.tv_vote_info)).check(matches(withText(dummyMovie.voteCount.toString())))
        onView(withId(R.id.tv_status_info)).check(matches(withText(dummyMovie.status)))
        onView(withId(R.id.tv_popularity_info)).check(
            matches(
                withText(
                    String.format(
                        "%.3f",
                        dummyMovie.popularity
                    )
                )
            )
        )
        onView(withId(R.id.tv_release_date_info)).check(matches(withText(dummyMovie.releaseDate)))
        onView(withId(R.id.tv_overview)).check(matches(withText(dummyMovie.overview)))
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
        onView(withId(R.id.fab_share)).check(matches(isDisplayed()))
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
        if (dummyTvShow.creators.isNotEmpty()) {
            onView(withId(R.id.rv_creators)).check(matches(isDisplayed()))
        }
        onView(withId(R.id.tv_name)).check(matches(withText(dummyTvShow.name)))
        onView(withId(R.id.tv_rating_info)).check(
            matches(
                withText(
                    String.format(
                        "%.1f",
                        dummyTvShow.voteAverage
                    )
                )
            )
        )
        onView(withId(R.id.tv_vote_info)).check(matches(withText(dummyTvShow.voteCount.toString())))
        onView(withId(R.id.tv_status_info)).check(matches(withText(dummyTvShow.status)))
        onView(withId(R.id.tv_popularity_info)).check(
            matches(
                withText(
                    String.format(
                        "%.3f",
                        dummyTvShow.popularity
                    )
                )
            )
        )
        onView(withId(R.id.tv_first_air_date_info)).check(matches(withText(dummyTvShow.firstAirDate)))
        onView(withId(R.id.tv_overview)).check(matches(withText(dummyTvShow.overview)))
    }
}