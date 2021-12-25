package com.xlwe.media.presentation.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.xlwe.media.databinding.ActivityCreateAccountBinding

class CreateAccountActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityCreateAccountBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}