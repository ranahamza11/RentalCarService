package com.drovisfrovis.rentalcarservice.uIBackend

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View

import androidx.appcompat.app.AppCompatActivity
import com.drovisfrovis.rentalcarservice.R


class SplashScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.setDecorFitsSystemWindows(false)
        } else {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        }

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        Handler(Looper.getMainLooper()).postDelayed(Runnable{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, 2300)

    }
}