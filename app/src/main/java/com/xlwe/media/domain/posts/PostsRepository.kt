package com.xlwe.media.domain.posts

import androidx.lifecycle.LiveData
import com.xlwe.media.domain.model.FeedPost

interface PostsRepository {
    fun getPostList(): LiveData<List<FeedPost>>
}