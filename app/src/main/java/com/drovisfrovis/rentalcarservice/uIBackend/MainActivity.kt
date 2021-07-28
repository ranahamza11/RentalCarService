package com.drovisfrovis.rentalcarservice.uIBackend

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.drovisfrovis.rentalcarservice.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_sign_in.setOnClickListener {
            startActivity(Intent(this, SignIn::class.java))
            finish()
        }

        btn_sign_up.setOnClickListener {
            startActivity(Intent(this, SignUp::class.java))
            finish()
        }
    }
}