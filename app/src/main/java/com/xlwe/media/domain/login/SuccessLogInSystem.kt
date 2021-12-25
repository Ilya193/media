package com.xlwe.media.domain.login

import androidx.lifecycle.LiveData

class SuccessLogInSystem(private val loginRepository: AuthenticationRepository) {
    fun okLogInSystem(): LiveData<Any> {
        return loginRepository.okLogInSystem()
    }
}