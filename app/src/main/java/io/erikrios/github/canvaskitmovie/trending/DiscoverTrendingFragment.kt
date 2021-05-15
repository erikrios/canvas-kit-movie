package io.erikrios.github.canvaskitmovie.trending

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import io.erikrios.github.canvaskitmovie.core.domain.model.Movie
import io.erikrios.github.canvaskitmovie.databinding.FragmentDiscoverTrendingBinding
import io.erikrios.github.canvaskitmovie.core.ui.CinemaAdapter
import io.erikrios.github.canvaskitmovie.main.MainViewModel
import io.erikrios.github.canvaskitmovie.core.data.Resource
import io.erikrios.github.canvaskitmovie.core.utils.Status

@AndroidEntryPoint
class DiscoverTrendingFragment : Fragment() {

    private var _binding: FragmentDiscoverTrendingBinding? = null
    private val binding get() = _binding
    private lateinit var adapter: CinemaAdapter<Movie>
    private val mainViewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDiscoverTrendingBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainViewModel.trendingState.observe(
            viewLifecycleOwner,
            this@DiscoverTrendingFragment::handleState
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
                DiscoverTrendingFragmentDirections.actionDiscoverTrendingFragmentToMovieDetailsFragment2(
                    movie
                )
            findNavController().navigate(action)
        }
        binding?.rvTrending?.adapter = adapter
    }
}