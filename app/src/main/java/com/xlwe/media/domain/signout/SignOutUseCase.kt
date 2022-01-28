package com.xlwe.media.domain.signout

class SignOutUseCase(
    private val signOutRepository: SignOutRepository
) {
    fun signOut() {
        signOutRepository.signOut()
    }
}