package com.xlwe.media.data.posts

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.xlwe.media.data.AppDatabase
import com.xlwe.media.domain.model.FeedPost
import com.xlwe.media.domain.posts.PostsRepository

class PostsRepositoryImpl: PostsRepository {
    private val db = AppDatabase.db
    private val auth = AppDatabase.auth

    private val postListLD = MutableLiveData<List<FeedPost>>()
    private val postList = mutableListOf<FeedPost>()

    override fun getPostList(): LiveData<List<FeedPost>> {
        updateList()

        return postListLD
    }

    private fun updateList() {
        db.child("feed-posts").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                postList.clear()

                for (push in snapshot.children) {
                    val post = push.getValue(FeedPost::class.java)
                    postList.add(post!!)
                }

                postListLD.value = postList.toList()
            }

            override fun onCancelled(error: DatabaseError) {}
        })
    }
}