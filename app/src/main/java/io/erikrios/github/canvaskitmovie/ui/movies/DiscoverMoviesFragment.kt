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
import io.erikrios.github.canvaskitmovie.utils.DummyData.generateMovies

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
        val movies = generateMovies()
        setRecyclerView(movies)
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
}