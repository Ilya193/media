package com.xlwe.media.data.communication

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.xlwe.media.data.AppDatabase
import com.xlwe.media.domain.communication.UserItemRepository
import com.xlwe.media.domain.model.User

class UserItemRepositoryImpl: UserItemRepository {
    private val db = AppDatabase.db
    private val auth = AppDatabase.auth

    private val userListLD = MutableLiveData<List<User>>()
    private val userList = mutableListOf<User>()

    init {
        updateList()
    }

    override fun getUserList(): LiveData<List<User>> {
        return userListLD
    }

    private fun updateList() {
        db.child("users").addValueEventListener(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                userList.clear()
                for (user in snapshot.children) {
                    val currUser = user.getValue(User::class.java)!!
                    if (currUser.id != auth.uid)
                        userList.add(currUser)
                }

                userListLD.value = userList.toList()
            }

            override fun onCancelled(error: DatabaseError) {}
        })
    }
}