package com.xlwe.media.domain.send

import com.xlwe.media.domain.model.Message

class DeleteMessageUseCase(
    private val sendRepository: SendRepository
) {
    fun deleteMessage(message: Message, received: String) {
        sendRepository.deleteMessage(message, received)
    }
}