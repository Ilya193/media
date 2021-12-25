package com.xlwe.media.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xlwe.media.domain.login.LogInSystemUseCase
import com.xlwe.media.domain.login.RegisteredUseCase
import com.xlwe.media.domain.login.SuccessLogInSystem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthenticationViewModel @Inject constructor(
    private val registeredUseCase: RegisteredUseCase,
    private val logInSystem: LogInSystemUseCase,
    private val successLogInSystem: SuccessLogInSystem,
): ViewModel() {
    private val _isRegistered = MutableLiveData<Boolean>()
    val isRegistered: LiveData<Boolean>
        get() = _isRegistered

    val okLogIn = successLogInSystem.okLogInSystem()

    fun registered() {
        viewModelScope.launch {
            _isRegistered.value = registeredUseCase.registered()
        }
    }

    fun logInSystem(email: String, password: String) {
        viewModelScope.launch {
            logInSystem.logInSystem(email, password)
        }
    }
}