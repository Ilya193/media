package com.xlwe.media.domain.signout

import androidx.lifecycle.LiveData
import com.google.firebase.auth.FirebaseUser

interface SignOutRepository {
    fun signOut()
    fun okSignOut(): LiveData<FirebaseUser>
}