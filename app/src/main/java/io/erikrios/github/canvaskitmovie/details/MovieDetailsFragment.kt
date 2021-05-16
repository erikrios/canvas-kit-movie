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
import io.erikrios.github.canvaskitmovie.core.data.Resource
import io.erikrios.github.canvaskitmovie.core.domain.model.Genre
import io.erikrios.github.canvaskitmovie.core.domain.model.Movie
import io.erikrios.github.canvaskitmovie.core.ui.GenreAdapter
import io.erikrios.github.canvaskitmovie.core.utils.ImageConfigurations
import io.erikrios.github.canvaskitmovie.core.utils.ImageConfigurations.generateFullImageUrl
import io.erikrios.github.canvaskitmovie.dashboard.DashboardFragment
import io.erikrios.github.canvaskitmovie.databinding.FragmentMovieDetailsBinding
import org.koin.android.viewmodel.ext.android.viewModel

class MovieDetailsFragment : Fragment() {

    private var _binding: FragmentMovieDetailsBinding? = null
    private val binding get() = _binding
    private val args: MovieDetailsFragmentArgs by navArgs()
    private val viewModel: MovieDetailsViewModel by viewModel()
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
        movie = args.movie
        hideBottomNavigation()
        handleToolbar(args.movie.title)
        viewModel.apply {
            getMovie(movie?.id ?: -1).observe(
                viewLifecycleOwner,
                this@MovieDetailsFragment::handleState
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        showBottomNavigation()
    }

    private fun handleState(movieResource: Resource<Movie>) {
        when (movieResource) {
            is Resource.Loading -> handleLoadingState()
            is Resource.Error -> movieResource.message?.let { handleErrorState(it) }
            is Resource.Success -> movieResource.data?.let { handleSuccessState(it) }
        }
    }

    private fun handleIsFavoriteMovieExistsState(isExists: Boolean, newMovie: Movie) {
        if (isExists) {
            binding?.fabFavorite?.apply {
                setImageResource(R.drawable.ic_baseline_favorite_24)
                setOnClickListener { viewModel.setFavoriteMovie(newMovie, false) }
                viewModel.getMovie(movie?.id ?: -1).observe(
                    viewLifecycleOwner,
                    this@MovieDetailsFragment::handleState
                )
            }
        } else {
            binding?.fabFavorite?.apply {
                setImageResource(R.drawable.ic_baseline_favorite_border_24)
                setOnClickListener { viewModel.setFavoriteMovie(newMovie, false) }
                viewModel.getMovie(movie?.id ?: -1).observe(
                    viewLifecycleOwner,
                    this@MovieDetailsFragment::handleState
                )
            }
        }
    }

    private fun handleLoadingState() {
        val loadingMessage = getString(R.string.loading)
        binding?.apply {
            tvTitle.text = movie?.title
            tvRatingInfo.text = String.format("%.1f", movie?.voteAverage)
            rbVoteAverage.rating = (movie?.voteAverage?.div(2.0))?.toFloat() ?: 0f
            tvVoteInfo.text = movie?.voteCount.toString()
            tvStatusInfo.text = loadingMessage
            tvPopularityInfo.text = String.format("%.3f", movie?.popularity)
            tvReleaseDateInfo.text = movie?.releaseDate
            tvOverview.text = movie?.overview
        }
    }

    private fun handleErrorState(message: String) {
        val noDataMessage = getString(R.string.no_data)
        binding?.apply {
            tvTitle.text = movie?.title
            tvRatingInfo.text = String.format("%.1f", movie?.voteAverage)
            rbVoteAverage.rating = (movie?.voteAverage?.div(2.0))?.toFloat() ?: 0f
            tvVoteInfo.text = movie?.voteCount.toString()
            tvStatusInfo.text = noDataMessage
            tvPopularityInfo.text = String.format("%.3f", movie?.popularity)
            tvReleaseDateInfo.text = movie?.releaseDate
            tvOverview.text = movie?.overview
        }
        Snackbar.make(
            requireActivity().findViewById(android.R.id.content),
            message,
            Snackbar.LENGTH_LONG
        ).show()
    }

    private fun handleSuccessState(movie: Movie) {
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
            rbVoteAverage.rating = (movie.voteAverage / 2.0).toFloat()
            tvVoteInfo.text = movie.voteCount.toString()
            tvStatusInfo.text = movie.status
            tvPopularityInfo.text = String.format("%.3f", movie.popularity)
            tvReleaseDateInfo.text = movie.releaseDate
            tvOverview.text = movie.overview
        }
        handleGenres(movie.genres ?: listOf())
        handleIsFavoriteMovieExistsState(this.movie?.isFavorite ?: movie.isFavorite, movie)
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