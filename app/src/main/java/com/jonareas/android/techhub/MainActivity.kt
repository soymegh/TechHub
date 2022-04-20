package com.jonareas.android.techhub

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.jonareas.android.techhub.databinding.ActivityMainBinding
import com.jonareas.android.techhub.utils.slideUpOnExit

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        installSplashScreen().apply(SplashScreen::slideUpOnExit)
        setContentView(binding.root)
    }

}