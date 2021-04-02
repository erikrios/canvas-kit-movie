package io.erikrios.github.canvaskitmovie.ui.view.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import io.erikrios.github.canvaskitmovie.data.model.Movie
import io.erikrios.github.canvaskitmovie.databinding.FragmentDiscoverMoviesBinding
import io.erikrios.github.canvaskitmovie.ui.adapter.CinemaAdapter
import io.erikrios.github.canvaskitmovie.ui.viewmodel.MainViewModel
import io.erikrios.github.canvaskitmovie.utils.Resource

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
        moviesResource.data?.let { setRecyclerView(it) }
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
}