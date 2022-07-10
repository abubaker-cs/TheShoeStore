package org.abubaker.shoesplanet

import android.os.Bundle
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

    //
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflate Layout: @layout/activity_main.xml
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

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
         * This will hide toolbar on specific fragments
         */
//        navController.addOnDestinationChangedListener { _, destination, _ ->
//            when (destination.id) {
//
//                // Hide Toolbar: Splash
//                R.id.splashFragment -> {
//                    supportActionBar?.hide()
//                    binding.toolbar.visibility = View.GONE
//                }
//
//                // Hide Toolbar:OnBoarding
//                R.id.onboardingFragment -> {
//                    supportActionBar?.hide()
//                    binding.toolbar.visibility = View.GONE
//                }
//
//                // Hide Toolbar:Sign in
//                R.id.signInFragment -> {
//                    supportActionBar?.hide()
//                    binding.toolbar.visibility = View.GONE
//                }
//
//                // Hide Toolbar: Sign up
//                R.id.signUpFragment -> {
//                    supportActionBar?.hide()
//                    binding.toolbar.visibility = View.GONE
//                }
//
//                // Display Toolbar
//                else -> {
//                    supportActionBar?.show()
//                    binding.toolbar.visibility = View.VISIBLE
//                }
//
//            }
//        }

    }

    /**
     * We will setup the toolbar with "log out" menu item, but we will also only display it in the "Shoe List" fragment.
     */
//    override fun onCreateOptionsMenu(menu: Menu): Boolean {
//
//        // Inflate the layout for menu: @res/menu/main_menu.xml
//        menuInflater.inflate(R.menu.main_menu, menu)
//
//        // Get reference to navHost
//        val navHostFragment =
//            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
//        val navController = navHostFragment.navController
//
//        // Get reference to Toolbar widget in the @layout/activity_main.xml
//        val toolbar = findViewById<Toolbar>(R.id.toolbar)
//
//        // This will get reference to the logout menu item stored in @res/menu/main_menu.xml
//        val menuItem = toolbar.menu.findItem(R.id.action_logout)
//
//        // We will be using a condition to only display the "logout" menu item on the "Shoe List" fragment.
//        navController.addOnDestinationChangedListener { _, destination, _ ->
//
//            when (destination.id) {
//
//                // Display the logout menu item on the "Shoe List" fragment.
//                R.id.shoeListFragment -> {
//                    menuItem.isVisible = true
//                }
//
//                // Hide the logout menu item from all other screens.
//                else -> {
//                    menuItem.isVisible = false
//                }
//
//            }
//        }
//
//        return true
//
//    }

//    // Here we will define what should happen when the user will click on the "Log out" menu item.
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//
//        // Action: Navigate the user to the to the "Sign In" fragment
//        if (item.itemId == R.id.action_logout) {
//            val navController = findNavController(R.id.nav_host_fragment)
//            navController.navigate(R.id.signInFragment)
//            return true
//        }
//
//        return super.onOptionsItemSelected(item)
//
//    }

    // Back Icon:
    // This will enable a < icon in the toolbar so the user can navigate back to the previous screen(s).
    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }

}