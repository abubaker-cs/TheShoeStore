package org.abubaker.shoesplanet

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import org.abubaker.shoesplanet.databinding.ActivitySigninBinding

class SignIn : AppCompatActivity() {

    private lateinit var binding: ActivitySigninBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySigninBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment

        val navController = navHostFragment.navController

        // SignUp: Create a new Account
        binding.btnCreateAccount.setOnClickListener {
            startActivity(Intent(this@SignIn, Signup::class.java))
        }

        // SignIn: Move to the Main page
        binding.btnSignIn.setOnClickListener {
            // startActivity(Intent(this@SignIn, MainActivity::class.java))

            // val navControl = findNavController(R.id.nav_host_fragment)
            // Navigation.findNavController(this, R.id.nav_host_fragment)
            // view.findNavController().navigate(R.id.MainActivity)

        }
    }


}