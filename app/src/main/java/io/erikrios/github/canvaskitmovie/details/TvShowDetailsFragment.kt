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
import io.erikrios.github.canvaskitmovie.databinding.FragmentTvShowDetailsBinding
import io.github.erikrios.canvaskitmovie.core.domain.model.Creator
import io.github.erikrios.canvaskitmovie.core.domain.model.Genre
import io.github.erikrios.canvaskitmovie.core.domain.model.TvShow
import io.github.erikrios.canvaskitmovie.core.ui.CreatorAdapter
import io.github.erikrios.canvaskitmovie.core.ui.GenreAdapter
import io.github.erikrios.canvaskitmovie.core.utils.ImageConfigurations
import io.github.erikrios.canvaskitmovie.core.utils.ImageConfigurations.generateFullImageUrl
import org.koin.android.viewmodel.ext.android.viewModel

class TvShowDetailsFragment : Fragment() {

    private var _binding: FragmentTvShowDetailsBinding? = null
    private val binding get() = _binding
    private val args: TvShowDetailsFragmentArgs by navArgs()
    private val viewModel: TvShowDetailsViewModel by viewModel()
    private var tvShow: TvShow? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTvShowDetailsBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tvShow = args.tvShow
        hideBottomNavigation()
        handleToolbar(args.tvShow.name)
        viewModel.apply {
            getTvShow(tvShow?.id ?: -1).observe(
                viewLifecycleOwner,
                this@TvShowDetailsFragment::handleState
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        showBottomNavigation()
    }

    private fun handleState(tvShowResource: io.github.erikrios.canvaskitmovie.core.data.Resource<TvShow>) {
        when (tvShowResource) {
            is io.github.erikrios.canvaskitmovie.core.data.Resource.Loading -> handleLoadingState()
            is io.github.erikrios.canvaskitmovie.core.data.Resource.Error -> tvShowResource.message?.let {
                handleErrorState(
                    it
                )
            }
            is io.github.erikrios.canvaskitmovie.core.data.Resource.Success -> tvShowResource.data?.let {
                handleSuccessState(
                    it
                )
            }
        }
    }

    private fun handleIsFavoriteTvShowExistsState(isExists: Boolean, newTvShow: TvShow) {
        if (isExists) {
            binding?.fabFavorite?.apply {
                setImageResource(R.drawable.ic_baseline_favorite_24)
                setOnClickListener { viewModel.setFavoriteTvShow(newTvShow, false) }
            }
        } else {
            binding?.fabFavorite?.apply {
                setImageResource(R.drawable.ic_baseline_favorite_border_24)
                setOnClickListener { viewModel.setFavoriteTvShow(newTvShow, true) }
            }
        }
    }

    private fun handleLoadingState() {
        val loadingMessage = getString(R.string.loading)
        binding?.apply {
            tvName.text = tvShow?.name
            tvRatingInfo.text = String.format("%.1f", tvShow?.voteAverage)
            rbVoteAverage.rating = (tvShow?.voteAverage?.div(2.0))?.toFloat() ?: 0f
            tvVoteInfo.text = tvShow?.voteCount.toString()
            tvStatusInfo.text = loadingMessage
            tvPopularityInfo.text = String.format("%.3f", tvShow?.popularity)
            tvFirstAirDateInfo.text = tvShow?.firstAirDate
            tvOverview.text = tvShow?.overview
        }
    }

    private fun handleErrorState(message: String) {
        val noDataMessage = getString(R.string.no_data)
        binding?.apply {
            tvName.text = tvShow?.name
            tvRatingInfo.text = String.format("%.1f", tvShow?.voteAverage)
            rbVoteAverage.rating = (tvShow?.voteAverage?.div(2.0))?.toFloat() ?: 0f
            tvVoteInfo.text = tvShow?.voteCount.toString()
            tvStatusInfo.text = noDataMessage
            tvPopularityInfo.text = String.format("%.3f", tvShow?.popularity)
            tvFirstAirDateInfo.text = tvShow?.firstAirDate
            tvOverview.text = tvShow?.overview
        }
        Snackbar.make(
            requireActivity().findViewById(android.R.id.content),
            message,
            Snackbar.LENGTH_LONG
        ).show()
    }

    private fun handleSuccessState(tvShow: TvShow) {
        this.tvShow?.isFavorite = tvShow.isFavorite
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
            tvName.text = tvShow.name
            tvRatingInfo.text = String.format("%.1f", tvShow.voteAverage)
            rbVoteAverage.rating = (tvShow.voteAverage / 2.0).toFloat()
            tvVoteInfo.text = tvShow.voteCount.toString()
            tvStatusInfo.text = tvShow.status
            tvPopularityInfo.text = String.format("%.3f", tvShow.popularity)
            tvFirstAirDateInfo.text = tvShow.firstAirDate
            tvOverview.text = tvShow.overview
        }
        handleGenres(tvShow.genres ?: listOf())
        handleCreators(tvShow.creators ?: listOf())
        handleIsFavoriteTvShowExistsState(this.tvShow?.isFavorite ?: tvShow.isFavorite, tvShow)
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
                    .setText(tvShow?.overview)
                    .startChooser()
                return@setOnMenuItemClickListener true
            }
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