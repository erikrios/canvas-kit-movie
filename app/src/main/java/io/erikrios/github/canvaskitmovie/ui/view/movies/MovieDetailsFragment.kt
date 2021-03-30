package io.erikrios.github.canvaskitmovie.ui.view.movies

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import io.erikrios.github.canvaskitmovie.R
import io.erikrios.github.canvaskitmovie.data.model.Genre
import io.erikrios.github.canvaskitmovie.data.model.Movie
import io.erikrios.github.canvaskitmovie.databinding.FragmentMovieDetailsBinding
import io.erikrios.github.canvaskitmovie.ui.adapter.GenreAdapter
import io.erikrios.github.canvaskitmovie.ui.viewmodel.MainViewModel
import io.erikrios.github.canvaskitmovie.utils.ImageConfigurations
import io.erikrios.github.canvaskitmovie.utils.ImageConfigurations.generateFullImageUrl

@AndroidEntryPoint
class MovieDetailsFragment : Fragment() {

    private var _binding: FragmentMovieDetailsBinding? = null
    private val binding get() = _binding
    private val args: MovieDetailsFragmentArgs by navArgs()
    private val mainViewModel: MainViewModel by activityViewModels()

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
        mainViewModel.apply {
            getMovieById(args.movie.id)
            movieState.observe(viewLifecycleOwner, this@MovieDetailsFragment::handleState)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun handleState(movie: Movie?) {
        movie?.let { handleView(it) }
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
            toolbar.apply {
                title = movie.title
                navigationIcon =
                    ContextCompat.getDrawable(context, R.drawable.ic_baseline_arrow_back_24)
                setNavigationOnClickListener { findNavController().popBackStack() }
            }
            fabShare.setOnClickListener {
                val intent = Intent(Intent.ACTION_SEND)
                intent.putExtra(Intent.EXTRA_TEXT, movie.overview)
                intent.type = "text/plain"
                startActivity(intent)
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

    private fun handleGenres(genres: List<Genre>) {
        val genreAdapter = GenreAdapter(genres)
        binding?.rvGenres?.adapter = genreAdapter
    }
}