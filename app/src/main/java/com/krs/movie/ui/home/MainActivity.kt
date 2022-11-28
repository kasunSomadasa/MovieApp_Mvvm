package com.krs.movie.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.krs.movie.R
import com.krs.movie.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController = navHostFragment.navController

        // connect bottom navigation to nav graph
        NavigationUI.setupWithNavController(binding.bnvNews, navController, false)
        appBarConfiguration = AppBarConfiguration(
            topLevelDestinationIds = setOf(R.id.movieFragment, R.id.tvShowFragment),
            fallbackOnNavigateUpListener = ::onSupportNavigateUp
        )
        // connect toolbar with navigation
        binding.toolbar.setupWithNavController(navController, appBarConfiguration)
    }

}