package com.xlwe.media.domain.signout

import androidx.lifecycle.LiveData
import com.google.firebase.auth.FirebaseUser

class SuccessSignOutUseCase(
    private val signOutRepository: SignOutRepository
) {
    fun okSignOut(): LiveData<FirebaseUser> {
        return signOutRepository.okSignOut()
    }
}