package io.erikrios.github.canvaskitmovie.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.erikrios.github.canvaskitmovie.R
import io.erikrios.github.canvaskitmovie.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setTheme(R.style.Theme_CanvasKitMovie)
        setContentView(binding.root)
    }
}