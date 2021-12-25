package com.xlwe.media.domain.login

class LogInSystemUseCase(private val loginRepository: AuthenticationRepository) {
    fun logInSystem(email: String, password: String) {
        loginRepository.logInSystem(email, password)
    }
}