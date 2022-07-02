package org.abubaker.shoesplanet

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.abubaker.shoesplanet.databinding.ActivitySigninBinding

class SignIn : AppCompatActivity() {

    private lateinit var binding: ActivitySigninBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySigninBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // SignUp: Create a new Account
        binding.btnCreateAccount.setOnClickListener {
            startActivity(Intent(this@SignIn, Signup::class.java))
        }

        // SignIn: Move to the Main page
        binding.btnSignIn.setOnClickListener {
            startActivity(Intent(this@SignIn, MainActivity::class.java))
        }
    }


}