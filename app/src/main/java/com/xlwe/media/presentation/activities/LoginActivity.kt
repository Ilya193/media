package com.xlwe.media.presentation.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.xlwe.media.databinding.ActivityLoginBinding
import com.xlwe.media.presentation.viewmodels.AuthenticationViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityLoginBinding.inflate(layoutInflater)
    }

    private val loginViewModel: AuthenticationViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        loginViewModel.registered()

        observeViewModel()
        setClickListeners()
    }

    private fun observeViewModel() {
        loginViewModel.isRegistered.observe(this) {
            if (it) {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
        }

        loginViewModel.okLogIn.observe(this) {

            if (it is String) {
                Toast.makeText(this, "$it", Toast.LENGTH_LONG).show()
            }

            if (it is Boolean) {
                loginViewModel.registered()
            }
        }
    }

    private fun setClickListeners() {
        binding.signIn.setOnClickListener {
            val email = binding.email.text.toString()
            val password = binding.password.text.toString()
            if (email.isNotEmpty() && password.isNotEmpty()) {
                loginViewModel.logInSystem(email, password)
            }
            else {
                Toast.makeText(this, "Заполните поля", Toast.LENGTH_SHORT).show()
            }
        }

        binding.signup.setOnClickListener {
            startActivity(Intent(this, CreateAccountActivity::class.java))
        }
    }
}