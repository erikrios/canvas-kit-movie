package io.erikrios.github.canvaskitmovie.ui.tvshows

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import io.erikrios.github.canvaskitmovie.databinding.FragmentTvShowsBinding

class TvShowsFragment : Fragment() {

    private var _binding: FragmentTvShowsBinding? = null
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTvShowsBinding.inflate(inflater, container, false)
        return binding?.root
    }
}