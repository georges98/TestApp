package com.company.testapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class SplashScreenActivity : AppCompatActivity() {
     override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         startActivity(Intent(this@SplashScreenActivity, LoginFragment::class.java))
        finish()
    }
}