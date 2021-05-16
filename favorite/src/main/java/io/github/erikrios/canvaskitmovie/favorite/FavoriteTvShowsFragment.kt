package io.github.erikrios.canvaskitmovie.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.children
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import io.github.erikrios.canvaskitmovie.core.domain.model.TvShow
import io.github.erikrios.canvaskitmovie.core.ui.FavoriteCinemaAdapter
import io.github.erikrios.canvaskitmovie.core.utils.SortUtils
import io.github.erikrios.canvaskitmovie.favorite.databinding.FragmentFavoriteTvShowsBinding
import io.github.erikrios.canvaskitmovie.favorite.di.favoriteModule
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class FavoriteTvShowsFragment : Fragment() {

    private var _binding: FragmentFavoriteTvShowsBinding? = null
    private val binding get() = _binding
    private lateinit var adapter: FavoriteCinemaAdapter<TvShow>
    private val viewModel: FavoriteTvShowsViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        loadKoinModules(favoriteModule)
        _binding = FragmentFavoriteTvShowsBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        handleToolbar()
        viewModel.apply {
            getFavoriteTvShows(SortUtils.Sort.TITLE).observe(
                viewLifecycleOwner,
                this@FavoriteTvShowsFragment::handleState
            )
            handleAdapter()
            handleRecyclerView()
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.getFavoriteTvShows(SortUtils.Sort.TITLE)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun handleState(tvShowState: List<TvShow>?) {
        tvShowState?.let { handleSuccessState(it) } ?: run { handleLoadingState() }
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
            menu.children.iterator().forEach {
                when (it.itemId) {
                    R.id.action_title -> {
                        it.setOnMenuItemClickListener { menuItem ->
                            handleMenuItemClick(menuItem, SortUtils.Sort.TITLE)
                            return@setOnMenuItemClickListener true
                        }
                    }
                    R.id.action_release_date -> {
                        it.setOnMenuItemClickListener { menuItem ->
                            handleMenuItemClick(menuItem, SortUtils.Sort.RELEASE_DATE)
                            return@setOnMenuItemClickListener true
                        }
                    }
                    R.id.action_popularity -> {
                        it.setOnMenuItemClickListener { menuItem ->
                            handleMenuItemClick(menuItem, SortUtils.Sort.POPULARITY)
                            return@setOnMenuItemClickListener true
                        }
                    }
                    R.id.action_vote_average -> {
                        it.setOnMenuItemClickListener { menuItem ->
                            handleMenuItemClick(menuItem, SortUtils.Sort.VOTE_AVERAGE)
                            return@setOnMenuItemClickListener true
                        }
                    }
                    R.id.action_vote_count -> {
                        it.setOnMenuItemClickListener { menuItem ->
                            handleMenuItemClick(menuItem, SortUtils.Sort.VOTE_COUNT)
                            return@setOnMenuItemClickListener true
                        }
                    }
                    R.id.action_random -> {
                        it.setOnMenuItemClickListener { menuItem ->
                            handleMenuItemClick(menuItem, SortUtils.Sort.RANDOM)
                            return@setOnMenuItemClickListener true
                        }
                    }
                }
            }
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

    private fun handleMenuItemClick(menuItem: MenuItem, sort: SortUtils.Sort) {
        viewModel.getFavoriteTvShows(sort).observe(
            viewLifecycleOwner,
            this@FavoriteTvShowsFragment::handleState
        )
        menuItem.isChecked = true
    }
}