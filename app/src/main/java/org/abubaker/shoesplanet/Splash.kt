package org.abubaker.shoesplanet

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity

class Splash : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        val thread: Thread = object : Thread() {
            override fun run() {
                try {
                    sleep(5000)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                } finally {
                    startActivity(Intent(this@Splash, SignIn::class.java))
                }
            }
        }
        thread.start()

    }

}