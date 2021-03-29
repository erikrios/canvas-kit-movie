package io.erikrios.github.canvaskitmovie.ui.view.tvshows

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import io.erikrios.github.canvaskitmovie.R
import io.erikrios.github.canvaskitmovie.data.model.Creator
import io.erikrios.github.canvaskitmovie.data.model.Genre
import io.erikrios.github.canvaskitmovie.data.model.TvShow
import io.erikrios.github.canvaskitmovie.databinding.FragmentTvShowDetailsBinding
import io.erikrios.github.canvaskitmovie.ui.adapter.CreatorAdapter
import io.erikrios.github.canvaskitmovie.ui.adapter.GenreAdapter
import io.erikrios.github.canvaskitmovie.ui.tvshows.TvShowDetailsFragmentArgs
import io.erikrios.github.canvaskitmovie.utils.ImageConfigurations
import io.erikrios.github.canvaskitmovie.utils.ImageConfigurations.generateFullImageUrl

class TvShowDetailsFragment : Fragment() {

    private var _binding: FragmentTvShowDetailsBinding? = null
    private val binding get() = _binding
    private val args: TvShowDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTvShowDetailsBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        handleView(args.tvShow)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
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
            toolbar.apply {
                title = tvShow.name
                navigationIcon =
                    ContextCompat.getDrawable(context, R.drawable.ic_baseline_arrow_back_24)
                setNavigationOnClickListener { findNavController().popBackStack() }
            }
            fabShare.setOnClickListener {
                val intent = Intent(Intent.ACTION_SEND)
                intent.putExtra(Intent.EXTRA_TEXT, tvShow.overview)
                intent.type = "text/plain"
                startActivity(intent)
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
        handleCreators(tvShow.creators)
    }

    private fun handleGenres(genres: List<Genre>) {
        val genreAdapter = GenreAdapter(genres)
        binding?.rvGenres?.adapter = genreAdapter
    }

    private fun handleCreators(creators: List<Creator>) {
        val creatorAdapter = CreatorAdapter(creators)
        binding?.rvCreators?.adapter = creatorAdapter
    }
}