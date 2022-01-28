package com.xlwe.media.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xlwe.media.domain.model.Message
import com.xlwe.media.domain.send.DeleteMessageUseCase
import com.xlwe.media.domain.send.GetMessageListUseCase
import com.xlwe.media.domain.send.SendMessageUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SendViewModel @Inject constructor(
    private val getMessageListUseCase: GetMessageListUseCase,
    private val sendMessageUseCase: SendMessageUseCase,
    private val deleteMessageUseCase: DeleteMessageUseCase
): ViewModel() {
    lateinit var messageList: LiveData<List<Message>>

    fun getMessageList(received: String) {
        viewModelScope.launch {
            messageList = getMessageListUseCase.getMessageList(received)
        }
    }

    fun sendMessage(message: Message, received: String) {
        viewModelScope.launch {
            sendMessageUseCase.sendMessage(message, received)
        }
    }

    fun deleteMessage(message: Message, received: String) {
        viewModelScope.launch {
            deleteMessageUseCase.deleteMessage(message, received)
        }
    }
}