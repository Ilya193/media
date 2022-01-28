package com.xlwe.media.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.xlwe.media.domain.communication.GetUserListUseCase
import com.xlwe.media.domain.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getUserListUseCase: GetUserListUseCase
): ViewModel() {
    private val _userList = getUserListUseCase.getUserList()
    val userList: LiveData<List<User>>
        get() = _userList
}