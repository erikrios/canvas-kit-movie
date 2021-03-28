package io.erikrios.github.canvaskitmovie.ui.tvshows

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import io.erikrios.github.canvaskitmovie.data.model.TvShow
import io.erikrios.github.canvaskitmovie.databinding.FragmentDiscoverTvShowsBinding
import io.erikrios.github.canvaskitmovie.ui.adapter.CinemaAdapter
import io.erikrios.github.canvaskitmovie.utils.DummyData.generateTvShows

class DiscoverTvShowsFragment : Fragment() {

    private var _binding: FragmentDiscoverTvShowsBinding? = null
    private val binding get() = _binding
    private lateinit var adapter: CinemaAdapter<TvShow>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDiscoverTvShowsBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val tvShows = generateTvShows()
        setRecyclerView(tvShows)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
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