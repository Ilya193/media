package com.xlwe.media.presentation.adapters.postList

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.xlwe.media.databinding.PostItemBinding
import com.xlwe.media.domain.model.FeedPost

class PostListAdapter(private val context: Context): ListAdapter<FeedPost, PostListAdapter.PostItemViewHolder>(PostListDiffCallback()) {
    class PostItemViewHolder(val binding: PostItemBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostItemViewHolder {
        return PostItemViewHolder(PostItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: PostItemViewHolder, position: Int) {
        val post = getItem(position)
        with(holder) {
            Log.d("image", post.imageUrl)
            binding.caption.text = post.caption
            binding.imageContent.loadImage(post.imageUrl)
        }
    }

    private fun ImageView.loadImage(image: String) {
        Glide.with(this)
            .load(image)
            .centerCrop()
            .into(this)
    }
}