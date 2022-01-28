package com.xlwe.media.data.send

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.xlwe.media.data.AppDatabase
import com.xlwe.media.domain.model.Message
import com.xlwe.media.domain.model.User
import com.xlwe.media.domain.send.SendRepository

class SendRepositoryImpl: SendRepository {
    private val db = AppDatabase.db
    private val auth = AppDatabase.auth
    private val sender = auth.currentUser!!.uid

    private val messageListLD = MutableLiveData<List<Message>>()
    private val messageList = mutableListOf<Message>()

    private fun updateList(received: String) {
        db.child("chat/${sender+received}/messages").addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                messageList.clear()

                for (m in snapshot.children) {
                    val currMessage = m.getValue(Message::class.java)
                    messageList.add(currMessage!!)
                }

                Log.d("updateList", messageList.toString())

                messageListLD.value = messageList.toList()
            }

            override fun onCancelled(error: DatabaseError) {}
        })
    }

    override fun getMessageList(received: String): LiveData<List<Message>> {
        updateList(received)

        return messageListLD
    }

    override fun sendMessage(message: Message, received: String) {
        val senderId = auth.currentUser!!.uid

        val senderKey = db.child("chat/${senderId+received}/messages")
            .push()
            .key!!

        val receiveKey = db.child("chat/${received+senderId}/messages")
            .push()
            .key!!

        val currMessage = Message(message.message, senderId, senderKey, receiveKey)

        var receiveUser: User? = null

        db.child("users/$received").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val currUser = snapshot.getValue(User::class.java)!!
                receiveUser = currUser
            }
            override fun onCancelled(error: DatabaseError) {}
        })

        var senderUser: User? = null

        db.child("users/$senderId").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val currUser = snapshot.getValue(User::class.java)!!
                senderUser = currUser
            }
            override fun onCancelled(error: DatabaseError) {}
        })

        db.child("chat/${senderId+received}/messages/$senderKey")
            .setValue(currMessage)
            .addOnSuccessListener {
                db.child("chat/${received+senderId}/messages/$receiveKey")
                    .setValue(currMessage)
                    .addOnCompleteListener {
                        if (it.isSuccessful) {
                            db.child("users/$received")
                                .setValue(User(received, receiveUser!!.name, senderUser!!.id))
                        }
                    }
            }
    }

    override fun deleteMessage(message: Message, receivedId: String) {
        val senderId = auth.currentUser!!.uid

        db.child("chat/${senderId+receivedId}/messages/${message.senderKey}")
            .removeValue()
            .addOnSuccessListener {
                db.child("chat/${receivedId+senderId}/messages/${message.receiveKey}")
                    .removeValue()
            }
    }
}