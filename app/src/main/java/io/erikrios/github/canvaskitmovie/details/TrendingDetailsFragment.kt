package io.erikrios.github.canvaskitmovie.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ShareCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import io.erikrios.github.canvaskitmovie.R
import io.erikrios.github.canvaskitmovie.dashboard.DashboardFragment
import io.erikrios.github.canvaskitmovie.databinding.FragmentTrendingDetailsBinding
import io.github.erikrios.canvaskitmovie.core.data.Resource
import io.github.erikrios.canvaskitmovie.core.domain.model.Genre
import io.github.erikrios.canvaskitmovie.core.domain.model.Trending
import io.github.erikrios.canvaskitmovie.core.ui.GenreAdapter
import io.github.erikrios.canvaskitmovie.core.utils.ImageConfigurations
import io.github.erikrios.canvaskitmovie.core.utils.ImageConfigurations.generateFullImageUrl
import org.koin.android.viewmodel.ext.android.viewModel

class TrendingDetailsFragment : Fragment() {

    private var _binding: FragmentTrendingDetailsBinding? = null
    private val binding get() = _binding
    private val args: TrendingDetailsFragmentArgs by navArgs()
    private val viewModel: TrendingDetailsViewModel by viewModel()
    private var trending: Trending? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTrendingDetailsBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        trending = args.trending
        hideBottomNavigation()
        handleToolbar(args.trending.title)
        viewModel.apply {
            getTrending(trending?.id ?: -1).observe(
                viewLifecycleOwner,
                this@TrendingDetailsFragment::handleState
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        showBottomNavigation()
    }

    private fun handleState(trendingResource: Resource<Trending>) {
        when (trendingResource) {
            is Resource.Loading -> handleLoadingState()
            is Resource.Error -> trendingResource.message?.let { handleErrorState(it) }
            is Resource.Success -> trendingResource.data?.let { handleSuccessState(it) }
        }
    }

    private fun handleIsFavoriteTrendingExistsState(isExists: Boolean, newTrending: Trending) {
        if (isExists) {
            binding?.fabFavorite?.apply {
                setImageResource(R.drawable.ic_baseline_favorite_24)
                setOnClickListener { viewModel.setFavoriteTrending(newTrending, false) }
            }
        } else {
            binding?.fabFavorite?.apply {
                setImageResource(R.drawable.ic_baseline_favorite_border_24)
                setOnClickListener { viewModel.setFavoriteTrending(newTrending, true) }
            }
        }
    }

    private fun handleLoadingState() {
        val loadingMessage = getString(R.string.loading)
        binding?.apply {
            tvTitle.text = trending?.title
            tvRatingInfo.text = String.format("%.1f", trending?.voteAverage)
            rbVoteAverage.rating = (trending?.voteAverage?.div(2.0))?.toFloat() ?: 0f
            tvVoteInfo.text = trending?.voteCount.toString()
            tvStatusInfo.text = loadingMessage
            tvPopularityInfo.text = String.format("%.3f", trending?.popularity)
            tvReleaseDateInfo.text = trending?.releaseDate
            tvOverview.text = trending?.overview
        }
    }

    private fun handleErrorState(message: String) {
        val noDataMessage = getString(R.string.no_data)
        binding?.apply {
            tvTitle.text = trending?.title
            tvRatingInfo.text = String.format("%.1f", trending?.voteAverage)
            rbVoteAverage.rating = (trending?.voteAverage?.div(2.0))?.toFloat() ?: 0f
            tvVoteInfo.text = trending?.voteCount.toString()
            tvStatusInfo.text = noDataMessage
            tvPopularityInfo.text = String.format("%.3f", trending?.popularity)
            tvReleaseDateInfo.text = trending?.releaseDate
            tvOverview.text = trending?.overview
        }
        Snackbar.make(
            requireActivity().findViewById(android.R.id.content),
            message,
            Snackbar.LENGTH_LONG
        ).show()
    }

    private fun handleSuccessState(trending: Trending) {
        this.trending?.isFavorite = trending.isFavorite
        handleView(trending)
    }

    private fun handleView(trending: Trending) {
        binding?.apply {
            trending.backdropPath?.let {
                val imageUrl = generateFullImageUrl(it, ImageConfigurations.ImageType.BACKDROP)
                Glide.with(requireContext())
                    .load(imageUrl)
                    .into(imgBackdrop)
            }
            trending.posterPath?.let {
                val imageUrl = generateFullImageUrl(
                    it,
                    ImageConfigurations.ImageType.POSTER,
                    posterSize = ImageConfigurations.PosterSize.WIDTH_500
                )
                Glide.with(requireContext())
                    .load(imageUrl)
                    .into(imgPoster)
            }
            tvTitle.text = trending.title
            tvRatingInfo.text = String.format("%.1f", trending.voteAverage)
            rbVoteAverage.rating = (trending.voteAverage / 2.0).toFloat()
            tvVoteInfo.text = trending.voteCount.toString()
            tvStatusInfo.text = trending.status
            tvPopularityInfo.text = String.format("%.3f", trending.popularity)
            tvReleaseDateInfo.text = trending.releaseDate
            tvOverview.text = trending.overview
        }
        handleGenres(trending.genres ?: listOf())
        handleIsFavoriteTrendingExistsState(
            this.trending?.isFavorite ?: trending.isFavorite,
            trending
        )
    }

    private fun handleToolbar(title: String) {
        binding?.toolbar?.apply {
            this.title = title
            navigationIcon =
                ContextCompat.getDrawable(context, R.drawable.ic_baseline_arrow_back_24)
            setNavigationOnClickListener { findNavController().popBackStack() }
            menu.findItem(R.id.item_share).setOnMenuItemClickListener {
                val mimeType = "text/plain"
                ShareCompat.IntentBuilder
                    .from(requireActivity())
                    .setType(mimeType)
                    .setChooserTitle(getString(R.string.share_using))
                    .setText(trending?.overview)
                    .startChooser()
                return@setOnMenuItemClickListener true
            }
        }
    }

    private fun handleGenres(genres: List<Genre>) {
        val genreAdapter = GenreAdapter(genres)
        binding?.rvGenres?.adapter = genreAdapter
    }

    private fun hideBottomNavigation() {
        (parentFragment?.parentFragment?.parentFragment?.parentFragment as DashboardFragment).hideBottomNavigation()
    }

    private fun showBottomNavigation() {
        (parentFragment?.parentFragment?.parentFragment?.parentFragment as DashboardFragment).showBottomNavigation()
    }
}