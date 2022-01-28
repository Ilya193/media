package com.xlwe.media.data.signout

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseUser
import com.xlwe.media.data.AppDatabase
import com.xlwe.media.domain.signout.SignOutRepository

class SignOutRepositoryImpl: SignOutRepository {
    private val auth = AppDatabase.auth
    private val okSignOut = MutableLiveData<FirebaseUser>(auth.currentUser)

    override fun signOut() {
        auth.signOut()
    }

    override fun okSignOut(): LiveData<FirebaseUser> {
        return okSignOut
    }
}