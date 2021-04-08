package io.erikrios.github.canvaskitmovie.ui.view.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import io.erikrios.github.canvaskitmovie.R
import io.erikrios.github.canvaskitmovie.data.model.Movie
import io.erikrios.github.canvaskitmovie.databinding.FragmentFavoriteMoviesBinding
import io.erikrios.github.canvaskitmovie.ui.adapter.FavoriteCinemaAdapter
import io.erikrios.github.canvaskitmovie.utils.DummyData.generateMovies

class FavoriteMoviesFragment : Fragment() {

    private var _binding: FragmentFavoriteMoviesBinding? = null
    private val binding get() = _binding
    private lateinit var adapter: FavoriteCinemaAdapter<Movie>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavoriteMoviesBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        handleToolbar()
        handleAdapter(generateMovies())
        handleRecyclerView()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun handleToolbar() {
        binding?.toolbar?.apply {
            navigationIcon =
                ContextCompat.getDrawable(context, R.drawable.ic_baseline_arrow_back_24)
            setNavigationOnClickListener { findNavController().popBackStack() }
        }
    }

    private fun handleAdapter(movies: List<Movie>) {
        adapter = FavoriteCinemaAdapter {
            Toast.makeText(context, it.title, Toast.LENGTH_SHORT).show()
        }
        adapter.setCinemas(movies)
    }

    private fun handleRecyclerView() {
        binding?.rvFavoriteMovies?.adapter = adapter
    }
}