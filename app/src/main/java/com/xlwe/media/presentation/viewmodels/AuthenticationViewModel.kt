package com.xlwe.media.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xlwe.media.domain.login.LogInSystemUseCase
import com.xlwe.media.domain.login.RegisteredUseCase
import com.xlwe.media.domain.login.SuccessLogInSystemUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthenticationViewModel @Inject constructor(
    private val registeredUseCase: RegisteredUseCase,
    private val logInSystemUseCase: LogInSystemUseCase,
    private val successLogInSystemUseCase: SuccessLogInSystemUseCase,
): ViewModel() {
    private val _isRegistered = MutableLiveData<Boolean>()
    val isRegistered: LiveData<Boolean>
        get() = _isRegistered

    val okLogIn = successLogInSystemUseCase.okLogInSystem()

    fun registered() {
        viewModelScope.launch {
            _isRegistered.value = registeredUseCase.registered()
        }
    }

    fun logInSystem(email: String, password: String) {
        viewModelScope.launch {
            logInSystemUseCase.logInSystem(email, password)
        }
    }
}