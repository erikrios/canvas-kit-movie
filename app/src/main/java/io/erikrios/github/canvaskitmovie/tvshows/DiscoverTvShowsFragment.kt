package io.erikrios.github.canvaskitmovie.tvshows

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import io.erikrios.github.canvaskitmovie.R
import io.erikrios.github.canvaskitmovie.core.data.Resource
import io.erikrios.github.canvaskitmovie.core.domain.model.TvShow
import io.erikrios.github.canvaskitmovie.core.ui.CinemaAdapter
import io.erikrios.github.canvaskitmovie.databinding.FragmentDiscoverTvShowsBinding
import org.koin.android.viewmodel.ext.android.viewModel

class DiscoverTvShowsFragment : Fragment() {

    private var _binding: FragmentDiscoverTvShowsBinding? = null
    private val binding get() = _binding
    private lateinit var adapter: CinemaAdapter<TvShow>
    private val viewModel: DiscoverTvShowsViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDiscoverTvShowsBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        handleToolbar()
        viewModel.tvShowsState.observe(
            viewLifecycleOwner,
            this@DiscoverTvShowsFragment::handleState
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun handleState(tvShowsResource: Resource<List<TvShow>>) {
        when (tvShowsResource) {
            is Resource.Loading -> handleLoadingState(true)
            is Resource.Error -> {
                handleLoadingState(false)
                tvShowsResource.message?.let { handleErrorState(it) }
            }
            is Resource.Success -> {
                handleLoadingState(false)
                tvShowsResource.data?.let { handleSuccessState(it) }
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

    private fun handleSuccessState(tvShows: List<TvShow>) {
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

    private fun handleToolbar() {
        val action =
            DiscoverTvShowsFragmentDirections.actionDiscoverTvShowsFragmentToFavoriteTvShowsFragment()
        binding?.toolbar?.menu?.findItem(R.id.item_favorites)?.setOnMenuItemClickListener {
            findNavController().navigate(action)
            return@setOnMenuItemClickListener true
        }
    }
}