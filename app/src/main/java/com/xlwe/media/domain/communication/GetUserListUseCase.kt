package com.xlwe.media.domain.communication

import androidx.lifecycle.LiveData
import com.xlwe.media.domain.model.User

class GetUserListUseCase(
    private val userItemRepository: UserItemRepository
) {
    fun getUserList(): LiveData<List<User>> {
        return userItemRepository.getUserList()
    }
}