package com.xlwe.media.presentation.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.xlwe.media.R
import com.xlwe.media.databinding.FragmentPostsBinding
import com.xlwe.media.presentation.adapters.postList.PostListAdapter
import com.xlwe.media.presentation.viewmodels.PostsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PostsFragment : Fragment() {
    private var _binding: FragmentPostsBinding? = null
    private val binding: FragmentPostsBinding
        get() = _binding ?: throw RuntimeException("FragmentPostsBinding == null")

    private val viewModel: PostsViewModel by viewModels()

    private lateinit var postListAdapter: PostListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPostsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        observeViewModel()
    }

    private fun setupRecyclerView() {
        postListAdapter = PostListAdapter(context!!)
        binding.posts.adapter = postListAdapter
    }

    private fun observeViewModel() {
        viewModel.postList.observe(viewLifecycleOwner) {
            postListAdapter.submitList(it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}