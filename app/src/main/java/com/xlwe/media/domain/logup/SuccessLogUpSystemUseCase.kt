package com.xlwe.media.domain.logup

import androidx.lifecycle.LiveData

class SuccessLogUpSystemUseCase(private val registrationRepository: RegistrationRepository) {
    fun okLogInSystem(): LiveData<Any> {
        return registrationRepository.okLogUpSystem()
    }
}