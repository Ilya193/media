package com.xlwe.media.presentation.adapters.postList

import androidx.recyclerview.widget.DiffUtil
import com.xlwe.media.domain.model.FeedPost

class PostListDiffCallback: DiffUtil.ItemCallback<FeedPost>() {
    override fun areItemsTheSame(oldItem: FeedPost, newItem: FeedPost): Boolean {
        return oldItem.uid == newItem.uid
    }

    override fun areContentsTheSame(oldItem: FeedPost, newItem: FeedPost): Boolean {
        return oldItem == newItem
    }
}