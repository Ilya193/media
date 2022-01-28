package com.xlwe.media.presentation.adapters.messageList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.xlwe.media.R
import com.xlwe.media.domain.model.Message

class MessageListAdapter: ListAdapter<Message, MessageListAdapter.MessageItemViewHolder>(
    MessageListDiffCallback()
) {
    class MessageItemViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val tvName = view.findViewById<TextView>(R.id.tvMessage)
    }

    var onMessageItemLongClickListener: ((Message) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageItemViewHolder {
        val layout = when (viewType) {
            USER_SEND -> R.layout.user_send
            USER_RECEIVE -> R.layout.user_receive
            else -> throw RuntimeException("Unknown view type")
        }

        return MessageItemViewHolder(LayoutInflater.from(parent.context).inflate(layout, parent, false))
    }

    override fun onBindViewHolder(holder: MessageItemViewHolder, position: Int) {
        val user = FirebaseAuth.getInstance().currentUser?.uid
        val message = getItem(position)
        with(holder) {
            tvName.text = message.message
            itemView.setOnLongClickListener {
                if (user == message.sender)
                    onMessageItemLongClickListener?.invoke(message)
                true
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        val message = getItem(position)
        val user = FirebaseAuth.getInstance().currentUser?.uid
        if (user == message.sender) {
            return USER_SEND
        }
        else {
            return USER_RECEIVE
        }
    }

    companion object {
        const val USER_SEND = 1
        const val USER_RECEIVE = 2
    }
}