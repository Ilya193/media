package com.xlwe.media.presentation.adapters.messageList

import androidx.recyclerview.widget.DiffUtil
import com.xlwe.media.domain.model.Message

class MessageListDiffCallback: DiffUtil.ItemCallback<Message>() {
    override fun areItemsTheSame(oldItem: Message, newItem: Message): Boolean {
        return oldItem.sender == newItem.sender
    }

    override fun areContentsTheSame(oldItem: Message, newItem: Message): Boolean {
        return oldItem == newItem
    }
}