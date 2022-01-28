package com.xlwe.media.domain.sendPost

import android.net.Uri
import androidx.lifecycle.LiveData

class OkSendPostUseCase(
    private val sendPostRepository: SendPostRepository
) {
    fun okSendPost(): LiveData<Boolean> {
        return sendPostRepository.okSendPost()
    }
}