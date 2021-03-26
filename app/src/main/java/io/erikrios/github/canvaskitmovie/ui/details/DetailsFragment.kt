package io.erikrios.github.canvaskitmovie.ui.details

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
import io.erikrios.github.canvaskitmovie.data.model.Genre
import io.erikrios.github.canvaskitmovie.data.model.Movie
import io.erikrios.github.canvaskitmovie.databinding.FragmentDetailsBinding
import io.erikrios.github.canvaskitmovie.ui.adapter.GenreAdapter

class DetailsFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding
    private val args: DetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        handleView(args.movie)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun handleView(movie: Movie) {
        binding?.apply {
            Glide.with(requireContext())
                .load("https://image.tmdb.org/t/p/original${movie.backdropPath}")
                .into(imgBackdrop)
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
        }
        handleGenres(movie.genres ?: listOf())
    }

    private fun handleGenres(genres: List<Genre>) {
        val genreAdapter = GenreAdapter(genres)
        binding?.rvGenres?.adapter = genreAdapter
    }
}