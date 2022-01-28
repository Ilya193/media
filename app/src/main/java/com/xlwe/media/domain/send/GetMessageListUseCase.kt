package com.xlwe.media.domain.send

import androidx.lifecycle.LiveData
import com.xlwe.media.domain.model.Message

class GetMessageListUseCase(
    private val sendRepository: SendRepository
) {
    fun getMessageList(received: String): LiveData<List<Message>> {
        return sendRepository.getMessageList(received)
    }
}