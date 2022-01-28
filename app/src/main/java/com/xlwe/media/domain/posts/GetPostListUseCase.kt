package com.xlwe.media.domain.posts

import androidx.lifecycle.LiveData
import com.xlwe.media.domain.model.FeedPost

class GetPostListUseCase(private val postRepository: PostsRepository) {
    fun getPostList(): LiveData<List<FeedPost>> {
        return postRepository.getPostList()
    }
}