package com.xlwe.media.presentation.adapters.userList

import androidx.recyclerview.widget.DiffUtil
import com.xlwe.media.domain.model.User

class UserListDiffCallback: DiffUtil.ItemCallback<User>() {
    override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem == newItem
    }
}