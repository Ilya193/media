package com.xlwe.media.data.logup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.xlwe.media.data.AppDatabase
import com.xlwe.media.domain.logup.RegistrationRepository
import com.xlwe.media.domain.model.User

class RegistrationRepositoryImpl: RegistrationRepository {
    private val auth = AppDatabase.auth
    private val db = AppDatabase.db
    private val ok = MutableLiveData<Any>()

    override fun logUpSystem(email: String, password: String, name: String) {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
            if (it.isSuccessful) {
                val id = auth.currentUser!!.uid
                db.child("users/${id}")
                    .setValue(User(id, name))
                    .addOnCompleteListener {
                        ok.value = it.isSuccessful
                    }
            }
            else {
                ok.value = it.exception?.message
            }
        }
    }

    override fun okLogUpSystem(): LiveData<Any> {
        if (auth.currentUser != null)
            return ok

        ok.value = "TEST"
        return ok
    }
}