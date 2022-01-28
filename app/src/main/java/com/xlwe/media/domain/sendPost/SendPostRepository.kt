package com.xlwe.media.domain.sendPost

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

interface SendPostRepository {
    fun okSendPost(): LiveData<Boolean>
    fun sendPost(imageUri: Uri, text: String, _okSendPost: MutableLiveData<Boolean>)
}