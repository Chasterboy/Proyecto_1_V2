package com.example.proyecto_1_v2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class SplashActivity : AppCompatActivity() {

    private val SPLASH_TIME_OUT: Long = 3000 //3 segundos

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_splash)

        GlobalScope.launch {
            delay(SPLASH_TIME_OUT)
            startActivity(Intent(this@SplashActivity, MainActivity::class.java))
            finish()
        }
    }
}