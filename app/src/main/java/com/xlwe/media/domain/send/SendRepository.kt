package com.xlwe.media.domain.send

import androidx.lifecycle.LiveData
import com.xlwe.media.domain.model.Message

interface SendRepository {
    fun getMessageList(received: String): LiveData<List<Message>>
    fun sendMessage(message: Message, received: String)
    fun deleteMessage(message: Message, receivedId: String)
}