package com.xlwe.media.domain.login

class RegisteredUseCase(private val loginRepository: AuthenticationRepository) {
    fun registered(): Boolean {
        return loginRepository.registered()
    }
}