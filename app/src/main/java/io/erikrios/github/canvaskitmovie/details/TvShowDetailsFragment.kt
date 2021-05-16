package io.erikrios.github.canvaskitmovie.details

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
import io.erikrios.github.canvaskitmovie.R
import io.erikrios.github.canvaskitmovie.core.domain.model.Creator
import io.erikrios.github.canvaskitmovie.core.domain.model.Genre
import io.erikrios.github.canvaskitmovie.core.domain.model.TvShow
import io.erikrios.github.canvaskitmovie.databinding.FragmentTvShowDetailsBinding
import io.erikrios.github.canvaskitmovie.core.ui.CreatorAdapter
import io.erikrios.github.canvaskitmovie.core.ui.GenreAdapter
import io.erikrios.github.canvaskitmovie.dashboard.DashboardFragment
import io.erikrios.github.canvaskitmovie.core.utils.ImageConfigurations
import io.erikrios.github.canvaskitmovie.core.utils.ImageConfigurations.generateFullImageUrl
import io.erikrios.github.canvaskitmovie.core.data.Resource
import io.erikrios.github.canvaskitmovie.core.utils.Status

class TvShowDetailsFragment : Fragment() {
//
//    private var _binding: FragmentTvShowDetailsBinding? = null
//    private val binding get() = _binding
//    private val args: TvShowDetailsFragmentArgs by navArgs()
//    private val detailsViewModel: MovieDetailsViewModel by viewModels()
//    private var tvShow: TvShow? = null
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        _binding = FragmentTvShowDetailsBinding.inflate(inflater, container, false)
//        return binding?.root
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        val tvShow = args.tvShow
//        hideBottomNavigation()
//        handleToolbar(args.tvShow.name)
//        detailsViewModel.apply {
//            getTvShowById(tvShow.id)
//            isFavoriteTvShowExists(tvShow.id)
//            tvShowState.observe(viewLifecycleOwner, this@TvShowDetailsFragment::handleState)
//            isFavoriteTvShowExistsState.observe(
//                viewLifecycleOwner,
//                this@TvShowDetailsFragment::handleIsFavoriteTvShowExistsState
//            )
//        }
//    }
//
//    override fun onDestroyView() {
//        super.onDestroyView()
//        _binding = null
//        showBottomNavigation()
//    }
//
//    private fun handleState(tvShowResource: Resource<TvShow>) {
//        when (tvShowResource.status) {
//            Status.LOADING -> handleLoadingState()
//            Status.ERROR -> tvShowResource.message?.let { handleErrorState(it) }
//            Status.SUCCESS -> tvShowResource.data?.let { handleSuccessState(it) }
//        }
//    }
//
//    private fun handleIsFavoriteTvShowExistsState(isExists: Boolean) {
//        if (isExists) {
//            binding?.fabFavorite?.apply {
//                setImageResource(R.drawable.ic_baseline_favorite_24)
//                setOnClickListener { detailsViewModel.deleteFavoriteTvShow(tvShow ?: args.tvShow) }
//            }
//        } else {
//            binding?.fabFavorite?.apply {
//                setImageResource(R.drawable.ic_baseline_favorite_border_24)
//                setOnClickListener { detailsViewModel.insertFavoriteTvShow(tvShow ?: args.tvShow) }
//            }
//        }
//    }
//
//    private fun handleLoadingState() {
//        val loadingMessage = getString(R.string.loading)
//        binding?.apply {
//            tvName.text = loadingMessage
//            tvRatingInfo.text = String.format("%.1f", 0f)
//            rbVoteAverage.rating = 0f
//            tvVoteInfo.text = 0.toString()
//            tvStatusInfo.text = loadingMessage
//            tvPopularityInfo.text = String.format("%.3f", 0f)
//            tvFirstAirDateInfo.text = loadingMessage
//            tvOverview.text = loadingMessage
//        }
//    }
//
//    private fun handleErrorState(message: String) {
//        val noDataMessage = getString(R.string.no_data)
//        binding?.apply {
//            tvName.text = noDataMessage
//            tvRatingInfo.text = String.format("%.1f", 0f)
//            rbVoteAverage.rating = 0f
//            tvVoteInfo.text = 0.toString()
//            tvStatusInfo.text = noDataMessage
//            tvPopularityInfo.text = String.format("%.3f", 0f)
//            tvFirstAirDateInfo.text = noDataMessage
//            tvOverview.text = noDataMessage
//        }
//        Snackbar.make(
//            requireActivity().findViewById(android.R.id.content),
//            message,
//            Snackbar.LENGTH_LONG
//        ).show()
//    }
//
//    private fun handleSuccessState(tvShow: TvShow) {
//        this.tvShow = tvShow
//        handleView(tvShow)
//    }
//
//    private fun handleView(tvShow: TvShow) {
//        binding?.apply {
//            tvShow.backdropPath?.let {
//                val imageUrl = generateFullImageUrl(it, ImageConfigurations.ImageType.BACKDROP)
//                Glide.with(requireContext())
//                    .load(imageUrl)
//                    .into(imgBackdrop)
//            }
//            tvShow.posterPath?.let {
//                val imageUrl = generateFullImageUrl(
//                    it,
//                    ImageConfigurations.ImageType.POSTER,
//                    posterSize = ImageConfigurations.PosterSize.WIDTH_500
//                )
//                Glide.with(requireContext())
//                    .load(imageUrl)
//                    .into(imgPoster)
//            }
//            tvName.text = tvShow.name
//            tvRatingInfo.text = String.format("%.1f", tvShow.voteAverage)
//            rbVoteAverage.rating = (tvShow.voteAverage / 2.0).toFloat()
//            tvVoteInfo.text = tvShow.voteCount.toString()
//            tvStatusInfo.text = tvShow.status
//            tvPopularityInfo.text = String.format("%.3f", tvShow.popularity)
//            tvFirstAirDateInfo.text = tvShow.firstAirDate
//            tvOverview.text = tvShow.overview
//        }
//        handleGenres(tvShow.genres ?: listOf())
//        handleCreators(tvShow.creators ?: listOf())
//    }
//
//    private fun handleToolbar(title: String) {
//        binding?.toolbar?.apply {
//            this.title = title
//            navigationIcon =
//                ContextCompat.getDrawable(context, R.drawable.ic_baseline_arrow_back_24)
//            setNavigationOnClickListener { findNavController().popBackStack() }
//            menu.findItem(R.id.item_share).setOnMenuItemClickListener {
//                val mimeType = "text/plain"
//                ShareCompat.IntentBuilder
//                    .from(requireActivity())
//                    .setType(mimeType)
//                    .setChooserTitle(getString(R.string.share_using))
//                    .setText(tvShow?.overview)
//                    .startChooser()
//                return@setOnMenuItemClickListener true
//            }
//        }
//    }
//
//    private fun handleGenres(genres: List<Genre>) {
//        val genreAdapter = GenreAdapter(genres)
//        binding?.rvGenres?.adapter = genreAdapter
//    }
//
//    private fun handleCreators(creators: List<Creator>) {
//        val creatorAdapter = CreatorAdapter(creators)
//        binding?.rvCreators?.adapter = creatorAdapter
//    }
//
//    private fun hideBottomNavigation() {
//        (parentFragment?.parentFragment?.parentFragment?.parentFragment as DashboardFragment).hideBottomNavigation()
//    }
//
//    private fun showBottomNavigation() {
//        (parentFragment?.parentFragment?.parentFragment?.parentFragment as DashboardFragment).showBottomNavigation()
//    }
}