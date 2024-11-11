package com.example.mobilenews.ui.Home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mobilenews.databinding.ActivityMainBinding

class HomeActivity: AppCompatActivity() {

    private lateinit var binding:   ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        super.onCreate(savedInstanceState)
    }

}