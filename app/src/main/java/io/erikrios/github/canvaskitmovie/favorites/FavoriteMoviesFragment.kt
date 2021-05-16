package io.erikrios.github.canvaskitmovie.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.children
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.paging.PagedList
import dagger.hilt.android.AndroidEntryPoint
import io.erikrios.github.canvaskitmovie.R
import io.erikrios.github.canvaskitmovie.core.domain.model.Movie
import io.erikrios.github.canvaskitmovie.databinding.FragmentFavoriteMoviesBinding
import io.erikrios.github.canvaskitmovie.core.ui.FavoriteCinemaAdapter
import io.erikrios.github.canvaskitmovie.core.utils.SortUtils

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
            getFavoriteMovies(SortUtils.Sort.TITLE).observe(
                viewLifecycleOwner,
                this@FavoriteMoviesFragment::handleState
            )
            handleAdapter()
            handleRecyclerView()
        }
    }

    override fun onResume() {
        super.onResume()
        favoritesViewModel.getFavoriteMovies(SortUtils.Sort.TITLE)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun handleState(moviesState: PagedList<Movie>?) {
        moviesState?.let { handleSuccessState(it) } ?: run { handleLoadingState() }
    }

    private fun handleLoadingState() {
        binding?.progressBar?.visibility = View.VISIBLE
    }

    private fun handleSuccessState(moviesPagedList: PagedList<Movie>) {
        binding?.progressBar?.visibility = View.GONE
        if (moviesPagedList.isEmpty()) {
            binding?.apply {
                lavEmpty.visibility = View.VISIBLE
                rvFavoriteMovies.visibility = View.GONE
            }
        } else {
            binding?.apply {
                lavEmpty.visibility = View.GONE
                rvFavoriteMovies.visibility = View.VISIBLE
            }
            adapter.submitList(moviesPagedList)
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
                FavoriteMoviesFragmentDirections.actionFavoriteMoviesFragmentToMovieDetailsFragment(
                    it
                )
            findNavController().navigate(action)
        }
    }

    private fun handleRecyclerView() {
        binding?.rvFavoriteMovies?.adapter = adapter
    }

    private fun handleMenuItemClick(menuItem: MenuItem, sort: SortUtils.Sort) {
        favoritesViewModel.getFavoriteMovies(sort).observe(
            viewLifecycleOwner,
            this@FavoriteMoviesFragment::handleState
        )
        menuItem.isChecked = true
    }
}