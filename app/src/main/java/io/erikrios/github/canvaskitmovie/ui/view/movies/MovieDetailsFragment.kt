package io.erikrios.github.canvaskitmovie.ui.view.movies

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
import io.erikrios.github.canvaskitmovie.data.model.Genre
import io.erikrios.github.canvaskitmovie.data.model.Movie
import io.erikrios.github.canvaskitmovie.databinding.FragmentMovieDetailsBinding
import io.erikrios.github.canvaskitmovie.ui.adapter.GenreAdapter
import io.erikrios.github.canvaskitmovie.ui.view.dashboard.DashboardFragment
import io.erikrios.github.canvaskitmovie.ui.viewmodel.DetailsViewModel
import io.erikrios.github.canvaskitmovie.utils.ImageConfigurations
import io.erikrios.github.canvaskitmovie.utils.ImageConfigurations.generateFullImageUrl
import io.erikrios.github.canvaskitmovie.utils.Resource
import io.erikrios.github.canvaskitmovie.utils.Status

@AndroidEntryPoint
class MovieDetailsFragment : Fragment() {

    private var _binding: FragmentMovieDetailsBinding? = null
    private val binding get() = _binding
    private val args: MovieDetailsFragmentArgs by navArgs()
    private val detailsViewModel: DetailsViewModel by viewModels()
    private var movie: Movie? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMovieDetailsBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val movie = args.movie
        hideBottomNavigation()
        handleToolbar(args.movie.title)
        detailsViewModel.apply {
            getMovieById(movie.id)
            isFavoriteMovieExists(movie.id)
            movieState.observe(viewLifecycleOwner, this@MovieDetailsFragment::handleState)
            isFavoriteMovieExistsState.observe(
                viewLifecycleOwner,
                this@MovieDetailsFragment::handleIsFavoriteMovieExistsState
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        showBottomNavigation()
    }

    private fun handleState(movieResource: Resource<Movie>) {
        when (movieResource.status) {
            Status.LOADING -> handleLoadingState()
            Status.ERROR -> movieResource.message?.let { handleErrorState(it) }
            Status.SUCCESS -> movieResource.data?.let { handleSuccessState(it) }
        }
    }

    private fun handleIsFavoriteMovieExistsState(isExists: Boolean) {
        if (isExists) {
            binding?.fabFavorite?.apply {
                setImageResource(R.drawable.ic_baseline_favorite_24)
                setOnClickListener { detailsViewModel.deleteFavoriteMovie(movie ?: args.movie) }
            }
        } else {
            binding?.fabFavorite?.apply {
                setImageResource(R.drawable.ic_baseline_favorite_border_24)
                setOnClickListener { detailsViewModel.insertFavoriteMovie(movie ?: args.movie) }
            }
        }
    }

    private fun handleLoadingState() {
        val loadingMessage = getString(R.string.loading)
        binding?.apply {
            tvTitle.text = loadingMessage
            tvRatingInfo.text = String.format("%.1f", 0f)
            rbVoteAverage.rating = 0f
            tvVoteInfo.text = 0.toString()
            tvStatusInfo.text = loadingMessage
            tvPopularityInfo.text = String.format("%.3f", 0f)
            tvReleaseDateInfo.text = loadingMessage
            tvOverview.text = loadingMessage
        }
    }

    private fun handleErrorState(message: String) {
        val noDataMessage = getString(R.string.no_data)
        binding?.apply {
            tvTitle.text = noDataMessage
            tvRatingInfo.text = String.format("%.1f", 0f)
            rbVoteAverage.rating = 0f
            tvVoteInfo.text = 0.toString()
            tvStatusInfo.text = noDataMessage
            tvPopularityInfo.text = String.format("%.3f", 0f)
            tvReleaseDateInfo.text = noDataMessage
            tvOverview.text = noDataMessage
        }
        Snackbar.make(
            requireActivity().findViewById(android.R.id.content),
            message,
            Snackbar.LENGTH_LONG
        ).show()
    }

    private fun handleSuccessState(movie: Movie) {
        this.movie = movie
        handleView(movie)
    }

    private fun handleView(movie: Movie) {
        binding?.apply {
            movie.backdropPath?.let {
                val imageUrl = generateFullImageUrl(it, ImageConfigurations.ImageType.BACKDROP)
                Glide.with(requireContext())
                    .load(imageUrl)
                    .into(imgBackdrop)
            }
            movie.posterPath?.let {
                val imageUrl = generateFullImageUrl(
                    it,
                    ImageConfigurations.ImageType.POSTER,
                    posterSize = ImageConfigurations.PosterSize.WIDTH_500
                )
                Glide.with(requireContext())
                    .load(imageUrl)
                    .into(imgPoster)
            }
            tvTitle.text = movie.title
            tvRatingInfo.text = String.format("%.1f", movie.voteAverage)
            rbVoteAverage.rating = (movie.voteAverage / 3.333).toFloat()
            tvVoteInfo.text = movie.voteCount.toString()
            tvStatusInfo.text = movie.status
            tvPopularityInfo.text = String.format("%.3f", movie.popularity)
            tvReleaseDateInfo.text = movie.releaseDate
            tvOverview.text = movie.overview
        }
        handleGenres(movie.genres ?: listOf())
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
                    .setText(movie?.overview)
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