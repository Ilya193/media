package com.xlwe.media.data.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.xlwe.media.data.AppDatabase
import com.xlwe.media.domain.login.LoginRepository

class AuthenticationRepositoryImpl: LoginRepository {
    private val auth = AppDatabase.auth
    private val ok = MutableLiveData<Any>()

    override fun registered(): Boolean {
        if (auth.currentUser == null)
            return false
        return true
    }

    override fun logInSystem(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
            if (it.isSuccessful) {
                ok.value = it.isSuccessful
            }
            else {
                ok.value = it.exception?.message
            }
        }
    }

    override fun okLogInSystem(): LiveData<Any> {
        return ok
    }
}