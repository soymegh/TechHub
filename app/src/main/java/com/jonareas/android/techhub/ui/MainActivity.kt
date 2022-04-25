package com.jonareas.android.techhub.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.jonareas.android.techhub.R
import com.jonareas.android.techhub.databinding.ActivityMainBinding
import com.jonareas.android.techhub.utils.animation.slideUpOnExit
import com.jonareas.android.techhub.utils.gone
import com.jonareas.android.techhub.utils.visible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private val topLevelDestinations = setOf(R.id.exploreCoursesFragment,
        R.id.myCoursesFragment,
        R.id.searchCoursesFragment)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen().apply(SplashScreen::slideUpOnExit)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupNavigation()
    }

    private fun setupNavigation(): Unit = binding.run {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host)
                as NavHostFragment
        navController = navHostFragment.navController
        bottomNavigation.setupWithNavController(navController)

        lifecycleScope.launchWhenResumed {
            navController.addOnDestinationChangedListener { _, destination, _ ->
                when (destination.id) {
                    in topLevelDestinations -> bottomNavigation.visible()
                    else -> bottomNavigation.gone()
                }
            }
        }

    }

    override fun onNavigateUp(): Boolean =
        navController.navigateUp() || super.onNavigateUp()


}