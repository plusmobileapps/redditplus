package com.plusmobileapps.reddit

import android.os.Bundle
import android.view.View
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController

class MainActivity : AppCompatActivity() {

    private lateinit var bottom_navigation: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications))
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        bottom_navigation = findViewById(R.id.nav_view)
        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            when (destination.id) {
                R.id.navigation_home,
                R.id.navigation_dashboard,
                R.id.navigation_notifications -> showBottomNavigation()
                R.id.postOptionsBottomSheet -> Unit
                else -> hideBottomNavigation()
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.nav_host_fragment).navigateUp()
    }

    private fun hideBottomNavigation() {
        // bottom_navigation is BottomNavigationView
        with(bottom_navigation) {
            if (visibility == View.VISIBLE && alpha == 1f) {
                animate()
                    .translationY(300f)
                    .withEndAction { visibility = View.GONE }
                    .duration = 400
            }
        }
    }

    private fun showBottomNavigation() {
        // bottom_navigation is BottomNavigationView
        with(bottom_navigation) {
            visibility = View.VISIBLE
            animate()
                .translationY(0f)
                .duration = 400
        }
    }
}
