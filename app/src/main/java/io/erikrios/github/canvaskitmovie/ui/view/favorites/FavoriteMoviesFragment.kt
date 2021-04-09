package io.erikrios.github.canvaskitmovie.ui.view.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import io.erikrios.github.canvaskitmovie.R
import io.erikrios.github.canvaskitmovie.data.model.Movie
import io.erikrios.github.canvaskitmovie.databinding.FragmentFavoriteMoviesBinding
import io.erikrios.github.canvaskitmovie.ui.adapter.FavoriteCinemaAdapter
import io.erikrios.github.canvaskitmovie.ui.viewmodel.FavoritesViewModel
import io.erikrios.github.canvaskitmovie.utils.Resource
import io.erikrios.github.canvaskitmovie.utils.Status

@AndroidEntryPoint
class FavoriteMoviesFragment : Fragment() {

    private var _binding: FragmentFavoriteMoviesBinding? = null
    private val binding get() = _binding
    private lateinit var adapter: FavoriteCinemaAdapter<Movie>
    private val favoritesViewModel: FavoritesViewModel by viewModels()

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
        favoritesViewModel.apply {
            getFavoriteMovies()
            handleAdapter()
            handleRecyclerView()
            favoriteMoviesState.observe(
                viewLifecycleOwner,
                this@FavoriteMoviesFragment::handleState
            )
        }
    }

    override fun onResume() {
        super.onResume()
        favoritesViewModel.getFavoriteMovies()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun handleState(moviesState: Resource<List<Movie>>) {
        when (moviesState.status) {
            Status.SUCCESS -> moviesState.data?.let { handleSuccessState(it) }
            else -> handleLoadingState()
        }
    }

    private fun handleLoadingState() {
        binding?.progressBar?.visibility = View.VISIBLE
    }

    private fun handleSuccessState(movies: List<Movie>) {
        binding?.progressBar?.visibility = View.GONE
        adapter.setCinemas(movies)
    }

    private fun handleToolbar() {
        binding?.toolbar?.apply {
            navigationIcon =
                ContextCompat.getDrawable(context, R.drawable.ic_baseline_arrow_back_24)
            setNavigationOnClickListener { findNavController().popBackStack() }
        }
    }

    private fun handleAdapter() {
        adapter = FavoriteCinemaAdapter {
            val action =
                FavoriteMoviesFragmentDirections.actionFavoriteMoviesFragmentToMovieDetailsFragment(
                    it
                )
            findNavController().navigate(action)
        }
    }

    private fun handleRecyclerView() {
        binding?.rvFavoriteMovies?.adapter = adapter
    }
}