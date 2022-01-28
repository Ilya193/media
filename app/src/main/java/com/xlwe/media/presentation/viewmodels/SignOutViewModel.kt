package com.xlwe.media.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xlwe.media.domain.signout.SignOutUseCase
import com.xlwe.media.domain.signout.SuccessSignOutUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignOutViewModel @Inject constructor(
    private val signOutUseCase: SignOutUseCase,
    private val successSignOutUseCase: SuccessSignOutUseCase
): ViewModel() {

    val okSignOut = successSignOutUseCase.okSignOut()

    fun signOut() {
        viewModelScope.launch {
            signOutUseCase.signOut()
        }
    }
}