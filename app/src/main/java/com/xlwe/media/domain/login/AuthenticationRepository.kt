package com.xlwe.media.domain.login

import androidx.lifecycle.LiveData

interface AuthenticationRepository {
    fun registered(): Boolean
    fun logInSystem(email: String, password: String)
    fun okLogInSystem(): LiveData<Any>
 }