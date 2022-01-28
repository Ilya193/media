package com.xlwe.media.domain.logup

import androidx.lifecycle.LiveData

interface RegistrationRepository {
    fun logUpSystem(email: String, password: String, name: String)
    fun okLogUpSystem(): LiveData<Any>
}