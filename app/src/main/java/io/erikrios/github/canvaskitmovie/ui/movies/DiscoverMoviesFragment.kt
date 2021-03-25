package io.erikrios.github.canvaskitmovie.ui.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import io.erikrios.github.canvaskitmovie.data.model.Movie
import io.erikrios.github.canvaskitmovie.databinding.FragmentDiscoverMoviesBinding
import io.erikrios.github.canvaskitmovie.ui.adapter.MovieAdapter

class DiscoverMoviesFragment : Fragment() {

    private var _binding: FragmentDiscoverMoviesBinding? = null
    private val binding get() = _binding
    private lateinit var adapter: MovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDiscoverMoviesBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecyclerView(generateMovies())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setRecyclerView(movies: List<Movie>) {
        adapter = MovieAdapter(movies) { movie ->
            Toast.makeText(context, movie.originalTitle, Toast.LENGTH_SHORT).show()
        }
        binding?.rvMovies?.adapter = adapter
    }

    private fun generateMovies(): List<Movie> {
        val movies = mutableListOf<Movie>()
        for (i in 0..50) {
            val posterPath =
                if (i % 2 == 0) "/lPsD10PP4rgUGiGR4CCXA6iY0QQ.jpg" else "/tnAuB8q5vv7Ax9UAEje5Xi4BXik.jpg"
            movies.add(
                Movie(
                    id = i,
                    adult = true,
                    originalLanguage = "en",
                    title = if (i % 2 == 0) "Raya and the Last Dragon $i" else "Zack Snyder's Justice League $i",
                    originalTitle = "Zack Snyder's Justice League $i",
                    overview = "Determined $i to ensure Superman's ultimate sacrifice was not in vain, Bruce Wayne aligns forces with Diana Prince with plans to recruit a team of metahumans to protect the world from an approaching threat of catastrophic proportions.",
                    popularity = 11783.352,
                    posterPath = posterPath,
                    releaseDate = "2021-03-18",
                    voteAverage = if (i % 2 == 0) 10.0 else 4.2,
                    voteCount = 3389
                )
            )
        }
        return movies
    }
}