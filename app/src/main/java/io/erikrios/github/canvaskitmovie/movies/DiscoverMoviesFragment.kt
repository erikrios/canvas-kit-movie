package io.erikrios.github.canvaskitmovie.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import io.erikrios.github.canvaskitmovie.R
import io.erikrios.github.canvaskitmovie.databinding.FragmentDiscoverMoviesBinding
import io.github.erikrios.canvaskitmovie.core.domain.model.Movie
import io.github.erikrios.canvaskitmovie.core.ui.CinemaAdapter
import org.koin.android.viewmodel.ext.android.viewModel

class DiscoverMoviesFragment : Fragment() {

    private var _binding: FragmentDiscoverMoviesBinding? = null
    private val binding get() = _binding
    private lateinit var adapter: CinemaAdapter<Movie>
    private val viewModel: DiscoverMoviesViewModel by viewModel()

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
        viewModel.moviesState.observe(
            viewLifecycleOwner,
            this@DiscoverMoviesFragment::handleState
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun handleState(moviesResource: io.github.erikrios.canvaskitmovie.core.data.Resource<List<Movie>>) {
        when (moviesResource) {
            is io.github.erikrios.canvaskitmovie.core.data.Resource.Loading -> handleLoadingState(
                true
            )
            is io.github.erikrios.canvaskitmovie.core.data.Resource.Error -> {
                handleLoadingState(false)
                moviesResource.message?.let { handleErrorState(it) }
            }
            is io.github.erikrios.canvaskitmovie.core.data.Resource.Success -> {
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