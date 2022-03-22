package io.erikrios.github.canvaskitmovie.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import io.erikrios.github.canvaskitmovie.R
import io.erikrios.github.canvaskitmovie.databinding.FragmentDashboardBinding

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navController = requireActivity().findNavController(R.id.nav_host_fragment)
        binding?.bottomNavigation?.setupWithNavController(navController)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun hideBottomNavigation() {
        binding?.bottomNavigation?.visibility = View.GONE
    }

    fun showBottomNavigation() {
        binding?.bottomNavigation?.visibility = View.VISIBLE
    }
}