package com.xlwe.media.presentation.viewmodels

import androidx.lifecycle.ViewModel
import com.xlwe.media.domain.logup.LogUpSystemUseCase
import com.xlwe.media.domain.logup.SuccessLogUpSystemUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

@HiltViewModel
class RegistrationViewModel @Inject constructor(
    private val logUpSystemUseCase: LogUpSystemUseCase,
    private val successLogUpSystemUseCase: SuccessLogUpSystemUseCase
): ViewModel() {
    val okLogUp = successLogUpSystemUseCase.okLogInSystem()

    fun logUpSystem(email: String, password: String, name: String) {
        viewModelScope.launch {
            logUpSystemUseCase.logUpSystem(email, password, name)
        }
    }
}