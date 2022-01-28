package com.xlwe.media.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.xlwe.media.domain.model.FeedPost
import com.xlwe.media.domain.posts.GetPostListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PostsViewModel @Inject constructor(
    private val getPostListUseCase: GetPostListUseCase
): ViewModel() {
    private val _postList = getPostListUseCase.getPostList()
    val postList: LiveData<List<FeedPost>>
        get() = _postList
}