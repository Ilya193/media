package com.xlwe.media.domain.sendPost

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class SendPostUseCase(
    private val sendPostRepository: SendPostRepository
) {
    fun sendPost(imageUri: Uri, text: String, _okSendPost: MutableLiveData<Boolean>) {
        sendPostRepository.sendPost(imageUri, text, _okSendPost)
    }
}