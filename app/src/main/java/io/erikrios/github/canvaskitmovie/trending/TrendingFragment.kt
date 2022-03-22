package io.erikrios.github.canvaskitmovie.trending

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import io.erikrios.github.canvaskitmovie.databinding.FragmentTrendingBinding

class TrendingFragment : Fragment() {

    private val binding get() = _binding
    private var _binding: FragmentTrendingBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTrendingBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}