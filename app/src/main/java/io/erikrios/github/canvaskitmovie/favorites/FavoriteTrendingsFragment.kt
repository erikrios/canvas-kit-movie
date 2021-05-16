package io.erikrios.github.canvaskitmovie.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.children
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import io.erikrios.github.canvaskitmovie.R
import io.erikrios.github.canvaskitmovie.core.domain.model.Trending
import io.erikrios.github.canvaskitmovie.core.ui.FavoriteCinemaAdapter
import io.erikrios.github.canvaskitmovie.core.utils.SortUtils
import io.erikrios.github.canvaskitmovie.databinding.FragmentFavoriteTrendingsBinding
import org.koin.android.viewmodel.ext.android.viewModel

class FavoriteTrendingsFragment : Fragment() {

    private var _binding: FragmentFavoriteTrendingsBinding? = null
    private val binding get() = _binding
    private lateinit var adapter: FavoriteCinemaAdapter<Trending>
    private val viewModel: FavoriteTrendingsViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavoriteTrendingsBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        handleToolbar()
        viewModel.apply {
            getFavoriteTrendings(SortUtils.Sort.TITLE).observe(
                viewLifecycleOwner,
                this@FavoriteTrendingsFragment::handleState
            )
            handleAdapter()
            handleRecyclerView()
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.getFavoriteTrendings(SortUtils.Sort.TITLE)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun handleState(trendingsState: List<Trending>?) {
        trendingsState?.let { handleSuccessState(it) } ?: run { handleLoadingState() }
    }

    private fun handleLoadingState() {
        binding?.progressBar?.visibility = View.VISIBLE
    }

    private fun handleSuccessState(trendings: List<Trending>) {
        binding?.progressBar?.visibility = View.GONE
        if (trendings.isEmpty()) {
            binding?.apply {
                lavEmpty.visibility = View.VISIBLE
                rvFavoriteTrendings.visibility = View.GONE
            }
        } else {
            binding?.apply {
                lavEmpty.visibility = View.GONE
                rvFavoriteTrendings.visibility = View.VISIBLE
            }
            adapter.setCinemas(trendings)
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
                FavoriteTrendingsFragmentDirections.actionFavoriteTrendingsFragmentToTrendingDetailsFragment(
                    it
                )
            findNavController().navigate(action)
        }
    }

    private fun handleRecyclerView() {
        binding?.rvFavoriteTrendings?.adapter = adapter
    }

    private fun handleMenuItemClick(menuItem: MenuItem, sort: SortUtils.Sort) {
        viewModel.getFavoriteTrendings(sort).observe(
            viewLifecycleOwner,
            this@FavoriteTrendingsFragment::handleState
        )
        menuItem.isChecked = true
    }
}