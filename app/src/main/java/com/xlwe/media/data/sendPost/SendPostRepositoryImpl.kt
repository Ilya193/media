package com.xlwe.media.data.sendPost

import android.net.Uri
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.xlwe.media.data.AppDatabase
import com.xlwe.media.domain.model.FeedPost
import com.xlwe.media.domain.model.User
import com.xlwe.media.domain.sendPost.SendPostRepository

class SendPostRepositoryImpl: SendPostRepository {
    private val storage = AppDatabase.storage
    private val db = AppDatabase.db
    private val auth = AppDatabase.auth

    private val okSendLD = MutableLiveData<Boolean>()

    override fun okSendPost(): LiveData<Boolean> {
        return okSendLD
    }

    override fun sendPost(imageUri: Uri, text: String, _okSendPost: MutableLiveData<Boolean>){
        val uid = auth.currentUser!!.uid

        storage.child("users/$uid/images/${imageUri.lastPathSegment.toString()}")
            .putFile(imageUri)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    storage.child("users/$uid/images/${imageUri.lastPathSegment.toString()}")
                        .downloadUrl
                        .addOnSuccessListener {
                            val photoDownloadUrl = it.toString()
                            db.child("images/$uid").push().setValue(photoDownloadUrl)
                                .addOnCompleteListener {
                                    if (it.isSuccessful) {
                                        db.child("feed-posts")
                                            .push()
                                            .setValue(
                                                makeFeedPost(
                                                    uid,
                                                    photoDownloadUrl,
                                                    text
                                                )
                                            )
                                            .addOnCompleteListener {
                                                if (it.isSuccessful) {
                                                    _okSendPost.value = true
                                                }
                                            }
                                        }
                                    }
                                }
                        }
                }
    }

    private fun makeFeedPost(uid: String, photoDownloadUrl: String, text: String): FeedPost {
        return FeedPost(
            uid = uid,
            caption = text,
            imageUrl = photoDownloadUrl
        )
    }
}