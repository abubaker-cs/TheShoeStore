package org.abubaker.shoesplanet

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import org.abubaker.shoesplanet.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflate Layout: @layout/activity_main.xml
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        //
        binding.lifecycleOwner = this


        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment

        val navController = navHostFragment.navController

        /**
         * This will allow us to hide the < Back button from the shoe_list_fragment
         * Reference: https://developer.android.com/guide/navigation/navigation-ui#appbarconfiguration
         */
        setSupportActionBar(binding.toolbar)

        // appBarConfiguration = AppBarConfiguration(setOf(R.id.shoeListFragment))
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

        /**
         * This will hide the toolbar on specific fragments
         */
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {

                // Hide Toolbar: Splash
                R.id.splashFragment -> {
                    supportActionBar?.hide()
                    binding.toolbar.visibility = View.GONE
                }

                // Hide Toolbar:OnBoarding
                R.id.onboardingFragment -> {
                    supportActionBar?.hide()
                    binding.toolbar.visibility = View.GONE
                }

                // Hide Toolbar:Sign in
                R.id.signInFragment -> {
                    supportActionBar?.hide()
                    binding.toolbar.visibility = View.GONE
                }

                // Hide Toolbar: Sign up
                R.id.signUpFragment -> {
                    supportActionBar?.hide()
                    binding.toolbar.visibility = View.GONE
                }

                // Display Toolbar
                else -> {
                    supportActionBar?.show()
                    binding.toolbar.visibility = View.VISIBLE
                }

            }
        }

    }

    // Back Icon:
    // This will enable a < icon in the toolbar so the user can navigate back to the previous screen(s).
    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }

}