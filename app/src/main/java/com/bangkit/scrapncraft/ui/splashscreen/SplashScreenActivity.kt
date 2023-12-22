package com.bangkit.scrapncraft.ui.splashscreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.bangkit.scrapncraft.R
import com.bangkit.scrapncraft.ui.register.RegisterActivity

class SplashScreenActivity : AppCompatActivity() {
    val handler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        handler.postDelayed({
            startActivity(Intent(this, RegisterActivity::class.java))
            finish()
        }, 1000)
    }
}