package io.erikrios.github.canvaskitmovie.ui.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import io.erikrios.github.canvaskitmovie.R
import io.erikrios.github.canvaskitmovie.databinding.ActivityMainBinding
import io.erikrios.github.canvaskitmovie.ui.viewmodel.MainViewModel

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setTheme(R.style.Theme_CanvasKitMovie_NoActionBar)
        setContentView(binding.root)
        mainViewModel.apply {
            getMovies()
            getTvShows()
        }
    }
}