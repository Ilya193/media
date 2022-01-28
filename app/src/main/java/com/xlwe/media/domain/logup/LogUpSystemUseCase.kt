package com.xlwe.media.domain.logup

class LogUpSystemUseCase(private val registrationRepository: RegistrationRepository) {
    fun logUpSystem(email: String, password: String, name: String) {
        registrationRepository.logUpSystem(email, password, name)
    }
}