package org.abubaker.shoesplanet

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
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

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        // val navController = findNavController(R.id.nav_host_fragment_content_main)
        // https://stackoverflow.com/questions/50502269/illegalstateexception-link-does-not-have-a-navcontroller-set
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment

        val navController = navHostFragment.navController
        // appBarConfiguration = AppBarConfiguration(navController.graph)

        // This will allow us to hide the < Back button from the shoe_list_fragment
        // Reference: https://developer.android.com/guide/navigation/navigation-ui#appbarconfiguration
        appBarConfiguration = AppBarConfiguration(setOf(R.id.shoeListFragment))
        setupActionBarWithNavController(navController, appBarConfiguration)

        /**
         * Hide toolbar on specific fragments
         */
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {

                R.id.splashFragment -> {
                    supportActionBar?.hide()
                    binding.toolbar.visibility = View.GONE
                }

                R.id.onboardingFragment -> {
                    supportActionBar?.hide()
                    binding.toolbar.visibility = View.GONE
                }

                R.id.signInFragment -> {
                    supportActionBar?.hide()
                    binding.toolbar.visibility = View.GONE
                }

                R.id.signUpFragment -> {
                    supportActionBar?.hide()
                    binding.toolbar.visibility = View.GONE
                }

                else -> {
                    supportActionBar?.show()
                    binding.toolbar.visibility = View.VISIBLE
                }

            }
        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {

        // Inflate the XML layout: res/menu/main_menu.xml
        menuInflater.inflate(R.menu.main_menu, menu)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        // Inflate the menu; this adds items to the action bar if it is present.
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        val menuItem = toolbar.menu.findItem(R.id.action_logout)


        /**
         * This code will only show the logout button on the shoeListFragment
         */
        navController.addOnDestinationChangedListener { _, destination, _ ->

            when (destination.id) {

                R.id.shoeListFragment -> {
                    menuItem.isVisible = true
                }

                else -> {
                    menuItem.isVisible = false
                }

            }
        }




        return true


    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        if (item.itemId == R.id.action_logout) {
            val navController = findNavController(R.id.nav_host_fragment)
            navController.navigate(R.id.signInFragment)
            return true
        }

        return super.onOptionsItemSelected(item)

    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }

}