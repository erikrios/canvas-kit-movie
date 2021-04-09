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
import io.erikrios.github.canvaskitmovie.data.model.TvShow
import io.erikrios.github.canvaskitmovie.databinding.FragmentFavoriteTvShowsBinding
import io.erikrios.github.canvaskitmovie.ui.adapter.FavoriteCinemaAdapter
import io.erikrios.github.canvaskitmovie.ui.viewmodel.FavoritesViewModel
import io.erikrios.github.canvaskitmovie.utils.Resource
import io.erikrios.github.canvaskitmovie.utils.Status

@AndroidEntryPoint
class FavoriteTvShowsFragment : Fragment() {

    private var _binding: FragmentFavoriteTvShowsBinding? = null
    private val binding get() = _binding
    private lateinit var adapter: FavoriteCinemaAdapter<TvShow>
    private val favoritesViewModel: FavoritesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavoriteTvShowsBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        handleToolbar()
        favoritesViewModel.apply {
            getFavoriteTvShows()
            handleAdapter()
            handleRecyclerView()
            favoriteTvShowsState.observe(
                viewLifecycleOwner,
                this@FavoriteTvShowsFragment::handleState
            )
        }
    }

    override fun onResume() {
        super.onResume()
        favoritesViewModel.getFavoriteTvShows()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun handleState(tvShowState: Resource<List<TvShow>>) {
        when (tvShowState.status) {
            Status.SUCCESS -> tvShowState.data?.let { handleSuccessState(it) }
            else -> handleLoadingState()
        }
    }

    private fun handleLoadingState() {
        binding?.progressBar?.visibility = View.VISIBLE
    }

    private fun handleSuccessState(tvShows: List<TvShow>) {
        binding?.progressBar?.visibility = View.GONE
        if (tvShows.isEmpty()) {
            binding?.apply {
                lavEmpty.visibility = View.VISIBLE
                rvFavoriteTvShows.visibility = View.GONE
            }
        } else {
            binding?.apply {
                lavEmpty.visibility = View.GONE
                rvFavoriteTvShows.visibility = View.VISIBLE
            }
            adapter.setCinemas(tvShows)
        }
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
                FavoriteTvShowsFragmentDirections.actionFavoriteTvShowsFragmentToTvShowDetailsFragment(
                    it
                )
            findNavController().navigate(action)
        }
    }

    private fun handleRecyclerView() {
        binding?.rvFavoriteTvShows?.adapter = adapter
    }
}