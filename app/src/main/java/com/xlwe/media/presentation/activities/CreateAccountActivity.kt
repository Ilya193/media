package com.xlwe.media.presentation.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.xlwe.media.databinding.ActivityCreateAccountBinding
import com.xlwe.media.presentation.viewmodels.RegistrationViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreateAccountActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityCreateAccountBinding.inflate(layoutInflater)
    }

    private val registrationViewModel: RegistrationViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        observeViewModel()
        setOnClickListener()
    }

    private fun setOnClickListener() {
        binding.enterBtn.setOnClickListener {
            val email = binding.email.text.toString()
            val password = binding.password.text.toString()
            val name = binding.name.text.toString()
            if (email.isNotEmpty() && password.isNotEmpty() && name.isNotEmpty()) {
                registrationViewModel.logUpSystem(email, password, name)
            }
            else {
                Toast.makeText(this, "Заполните поля", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun observeViewModel() {
        registrationViewModel.okLogUp.observe(this) {
            if (it is String) {
                if (it != "TEST")
                    Toast.makeText(this, "$it", Toast.LENGTH_LONG).show()
            }

            if (it is Boolean) {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
        }
    }
}