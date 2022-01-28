package com.xlwe.media.domain.communication

import androidx.lifecycle.LiveData
import com.xlwe.media.domain.model.User

interface UserItemRepository {
    fun getUserList(): LiveData<List<User>>
}