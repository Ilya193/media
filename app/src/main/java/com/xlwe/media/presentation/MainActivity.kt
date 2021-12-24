package com.xlwe.media.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.xlwe.media.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}