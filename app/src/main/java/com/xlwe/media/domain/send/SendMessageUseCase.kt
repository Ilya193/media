package com.xlwe.media.domain.send

import com.xlwe.media.domain.model.Message

class SendMessageUseCase(
    private val sendRepository: SendRepository
) {
    fun sendMessage(message: Message, received: String) {
        sendRepository.sendMessage(message, received)
    }
}