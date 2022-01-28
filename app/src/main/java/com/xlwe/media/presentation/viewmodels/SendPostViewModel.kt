package com.xlwe.media.presentation.viewmodels

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xlwe.media.domain.sendPost.OkSendPostUseCase
import com.xlwe.media.domain.sendPost.SendPostUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SendPostViewModel @Inject constructor(
    private val okSendPostUseCase: OkSendPostUseCase,
    private val sendPostUseCase: SendPostUseCase
): ViewModel() {
    private  var _okSendPost = MutableLiveData<Boolean>()
    val okSendPost: LiveData<Boolean>
        get() = _okSendPost

    fun sendPost(imageUri: Uri, text: String) {
        viewModelScope.launch {
            sendPostUseCase.sendPost(imageUri, text, _okSendPost)
        }
    }
}