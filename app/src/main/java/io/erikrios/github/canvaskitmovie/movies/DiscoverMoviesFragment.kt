package io.erikrios.github.canvaskitmovie.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import io.erikrios.github.canvaskitmovie.R
import io.erikrios.github.canvaskitmovie.core.domain.model.Movie
import io.erikrios.github.canvaskitmovie.databinding.FragmentDiscoverMoviesBinding
import io.erikrios.github.canvaskitmovie.core.ui.CinemaAdapter
import io.erikrios.github.canvaskitmovie.main.MainViewModel
import io.erikrios.github.canvaskitmovie.core.data.Resource
import io.erikrios.github.canvaskitmovie.core.utils.Status

@AndroidEntryPoint
class DiscoverMoviesFragment : Fragment() {

    private var _binding: FragmentDiscoverMoviesBinding? = null
    private val binding get() = _binding
    private lateinit var adapter: CinemaAdapter<Movie>
    private val mainViewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDiscoverMoviesBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        handleToolbar()
        mainViewModel.moviesState.observe(
            viewLifecycleOwner,
            this@DiscoverMoviesFragment::handleState
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun handleState(moviesResource: Resource<List<Movie>>) {
        when (moviesResource.status) {
            Status.LOADING -> handleLoadingState(true)
            Status.ERROR -> {
                handleLoadingState(false)
                moviesResource.message?.let { handleErrorState(it) }
            }
            Status.SUCCESS -> {
                handleLoadingState(false)
                moviesResource.data?.let { handleSuccessState(it) }
            }
        }
    }

    private fun handleLoadingState(isLoading: Boolean) {
        if (isLoading) {
            binding?.apply {
                lavLoading.visibility = View.VISIBLE
                progressBar.visibility = View.VISIBLE
            }
        } else {
            binding?.apply {
                lavLoading.visibility = View.GONE
                progressBar.visibility = View.GONE
            }
        }
    }

    private fun handleErrorState(message: String) {
        Snackbar.make(
            requireActivity().findViewById(android.R.id.content),
            message,
            Snackbar.LENGTH_LONG
        ).show()
    }

    private fun handleSuccessState(movies: List<Movie>) {
        setRecyclerView(movies)
    }

    private fun setRecyclerView(movies: List<Movie>) {
        adapter = CinemaAdapter(movies) { movie ->
            val action =
                DiscoverMoviesFragmentDirections.actionDiscoverMoviesFragmentToMovieDetailsFragment(
                    movie
                )
            findNavController().navigate(action)
        }
        binding?.rvMovies?.adapter = adapter
    }

    private fun handleToolbar() {
        val action =
            DiscoverMoviesFragmentDirections.actionDiscoverMoviesFragmentToFavoriteMoviesFragment()
        binding?.toolbar?.menu?.findItem(R.id.item_favorites)?.setOnMenuItemClickListener {
            findNavController().navigate(action)
            return@setOnMenuItemClickListener true
        }
    }
}