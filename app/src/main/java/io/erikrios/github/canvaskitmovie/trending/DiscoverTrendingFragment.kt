package io.erikrios.github.canvaskitmovie.trending

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import io.erikrios.github.canvaskitmovie.core.data.Resource
import io.erikrios.github.canvaskitmovie.core.domain.model.Trending
import io.erikrios.github.canvaskitmovie.core.ui.CinemaAdapter
import io.erikrios.github.canvaskitmovie.databinding.FragmentDiscoverTrendingBinding
import org.koin.android.viewmodel.ext.android.viewModel

class DiscoverTrendingFragment : Fragment() {

    private var _binding: FragmentDiscoverTrendingBinding? = null
    private val binding get() = _binding
    private lateinit var adapter: CinemaAdapter<Trending>
    private val viewModel: DiscoverTrendingViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDiscoverTrendingBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.trendingsState.observe(
            viewLifecycleOwner,
            this@DiscoverTrendingFragment::handleState
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun handleState(trendingsResource: Resource<List<Trending>>) {
        when (trendingsResource) {
            is Resource.Loading -> handleLoadingState(true)
            is Resource.Error -> {
                handleLoadingState(false)
                trendingsResource.message?.let { handleErrorState(it) }
            }
            is Resource.Success -> {
                handleLoadingState(false)
                trendingsResource.data?.let { handleSuccessState(it) }
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

    private fun handleSuccessState(trendings: List<Trending>) {
        setRecyclerView(trendings)
    }

    private fun setRecyclerView(trendings: List<Trending>) {
        adapter = CinemaAdapter(trendings) { movie ->
            val action =
                DiscoverTrendingFragmentDirections.actionDiscoverTrendingFragmentToMovieDetailsFragment2(
                    movie
                )
            findNavController().navigate(action)
        }
        binding?.rvTrending?.adapter = adapter
    }
}