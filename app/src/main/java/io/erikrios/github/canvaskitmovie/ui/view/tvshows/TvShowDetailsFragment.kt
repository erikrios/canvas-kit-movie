package io.erikrios.github.canvaskitmovie.ui.view.tvshows

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ShareCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import io.erikrios.github.canvaskitmovie.R
import io.erikrios.github.canvaskitmovie.data.model.Creator
import io.erikrios.github.canvaskitmovie.data.model.Genre
import io.erikrios.github.canvaskitmovie.data.model.TvShow
import io.erikrios.github.canvaskitmovie.databinding.FragmentTvShowDetailsBinding
import io.erikrios.github.canvaskitmovie.ui.adapter.CreatorAdapter
import io.erikrios.github.canvaskitmovie.ui.adapter.GenreAdapter
import io.erikrios.github.canvaskitmovie.ui.view.dashboard.DashboardFragment
import io.erikrios.github.canvaskitmovie.ui.viewmodel.DetailsViewModel
import io.erikrios.github.canvaskitmovie.utils.ImageConfigurations
import io.erikrios.github.canvaskitmovie.utils.ImageConfigurations.generateFullImageUrl
import io.erikrios.github.canvaskitmovie.utils.Resource
import io.erikrios.github.canvaskitmovie.utils.Status

@AndroidEntryPoint
class TvShowDetailsFragment : Fragment() {

    private var _binding: FragmentTvShowDetailsBinding? = null
    private val binding get() = _binding
    private val args: TvShowDetailsFragmentArgs by navArgs()
    private val detailsViewModel: DetailsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTvShowDetailsBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        hideBottomNavigation()
        handleToolbar(args.tvShow.name)
        detailsViewModel.apply {
            getTvShowById(args.tvShow.id)
            tvShowState.observe(viewLifecycleOwner, this@TvShowDetailsFragment::handleState)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        showBottomNavigation()
    }

    private fun handleState(tvShowResource: Resource<TvShow>) {
        when (tvShowResource.status) {
            Status.LOADING -> handleLoadingState()
            Status.ERROR -> tvShowResource.message?.let { handleErrorState(it) }
            Status.SUCCESS -> tvShowResource.data?.let { handleSuccessState(it) }
        }
    }

    private fun handleLoadingState() {
        val loadingMessage = getString(R.string.loading)
        binding?.apply {
            tvName.text = loadingMessage
            tvRatingInfo.text = String.format("%.1f", 0f)
            rbVoteAverage.rating = 0f
            tvVoteInfo.text = 0.toString()
            tvStatusInfo.text = loadingMessage
            tvPopularityInfo.text = String.format("%.3f", 0f)
            tvFirstAirDateInfo.text = loadingMessage
            tvOverview.text = loadingMessage
        }
    }

    private fun handleErrorState(message: String) {
        val noDataMessage = getString(R.string.no_data)
        binding?.apply {
            tvName.text = noDataMessage
            tvRatingInfo.text = String.format("%.1f", 0f)
            rbVoteAverage.rating = 0f
            tvVoteInfo.text = 0.toString()
            tvStatusInfo.text = noDataMessage
            tvPopularityInfo.text = String.format("%.3f", 0f)
            tvFirstAirDateInfo.text = noDataMessage
            tvOverview.text = noDataMessage
        }
        Snackbar.make(
            requireActivity().findViewById(android.R.id.content),
            message,
            Snackbar.LENGTH_LONG
        ).show()
    }

    private fun handleSuccessState(tvShow: TvShow) {
        handleView(tvShow)
    }

    private fun handleView(tvShow: TvShow) {
        binding?.apply {
            tvShow.backdropPath?.let {
                val imageUrl = generateFullImageUrl(it, ImageConfigurations.ImageType.BACKDROP)
                Glide.with(requireContext())
                    .load(imageUrl)
                    .into(imgBackdrop)
            }
            tvShow.posterPath?.let {
                val imageUrl = generateFullImageUrl(
                    it,
                    ImageConfigurations.ImageType.POSTER,
                    posterSize = ImageConfigurations.PosterSize.WIDTH_500
                )
                Glide.with(requireContext())
                    .load(imageUrl)
                    .into(imgPoster)
            }
            fabShare.setOnClickListener {
                val mimeType = "text/plain"
                ShareCompat.IntentBuilder
                    .from(requireActivity())
                    .setType(mimeType)
                    .setChooserTitle(tvShow.name)
                    .setText(tvShow.overview)
                    .startChooser()
            }
            tvName.text = tvShow.name
            tvRatingInfo.text = String.format("%.1f", tvShow.voteAverage)
            rbVoteAverage.rating = (tvShow.voteAverage / 3.333).toFloat()
            tvVoteInfo.text = tvShow.voteCount.toString()
            tvStatusInfo.text = tvShow.status
            tvPopularityInfo.text = String.format("%.3f", tvShow.popularity)
            tvFirstAirDateInfo.text = tvShow.firstAirDate
            tvOverview.text = tvShow.overview
        }
        handleGenres(tvShow.genres ?: listOf())
        handleCreators(tvShow.creators ?: listOf())
    }

    private fun handleToolbar(title: String) {
        binding?.toolbar?.apply {
            this.title = title
            navigationIcon =
                ContextCompat.getDrawable(context, R.drawable.ic_baseline_arrow_back_24)
            setNavigationOnClickListener { findNavController().popBackStack() }
        }
    }

    private fun handleGenres(genres: List<Genre>) {
        val genreAdapter = GenreAdapter(genres)
        binding?.rvGenres?.adapter = genreAdapter
    }

    private fun handleCreators(creators: List<Creator>) {
        val creatorAdapter = CreatorAdapter(creators)
        binding?.rvCreators?.adapter = creatorAdapter
    }

    private fun hideBottomNavigation() {
        (parentFragment?.parentFragment?.parentFragment?.parentFragment as DashboardFragment).hideBottomNavigation()
    }

    private fun showBottomNavigation() {
        (parentFragment?.parentFragment?.parentFragment?.parentFragment as DashboardFragment).showBottomNavigation()
    }
}