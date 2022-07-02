package org.abubaker.shoesplanet

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.abubaker.shoesplanet.databinding.ActivitySignupBinding

class Signup : AppCompatActivity() {

    private lateinit var binding: ActivitySignupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Back to the SingIn Screen
        binding.btnBack.setOnClickListener {

            val i = Intent(this@Signup, SignIn::class.java)
            startActivity(i)

        }
    }
}