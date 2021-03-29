package io.erikrios.github.canvaskitmovie.ui.view.tvshows

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import io.erikrios.github.canvaskitmovie.data.model.TvShow
import io.erikrios.github.canvaskitmovie.databinding.FragmentDiscoverTvShowsBinding
import io.erikrios.github.canvaskitmovie.ui.adapter.CinemaAdapter
import io.erikrios.github.canvaskitmovie.ui.tvshows.DiscoverTvShowsFragmentDirections
import io.erikrios.github.canvaskitmovie.ui.viewmodel.MainViewModel

@AndroidEntryPoint
class DiscoverTvShowsFragment : Fragment() {

    private var _binding: FragmentDiscoverTvShowsBinding? = null
    private val binding get() = _binding
    private lateinit var adapter: CinemaAdapter<TvShow>
    private val mainViewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDiscoverTvShowsBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainViewModel.tvShowsState.observe(
            viewLifecycleOwner,
            this@DiscoverTvShowsFragment::handleState
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun handleState(tvShows: List<TvShow>) {
        setRecyclerView(tvShows)
    }

    private fun setRecyclerView(tvShows: List<TvShow>) {
        adapter = CinemaAdapter(tvShows) { tvShow ->
            val action =
                DiscoverTvShowsFragmentDirections.actionDiscoverTvShowsFragmentToTvShowDetailsFragment(
                    tvShow
                )
            findNavController().navigate(action)
        }
        binding?.rvTvShows?.adapter = adapter
    }
}