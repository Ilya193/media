package com.xlwe.media.presentation.fragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.xlwe.media.databinding.FragmentAddBinding
import com.xlwe.media.presentation.OnResultCamera
import com.xlwe.media.presentation.viewmodels.SendPostViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import java.lang.RuntimeException

@AndroidEntryPoint
class AddFragment : Fragment() {
    private var _binding: FragmentAddBinding? = null
    private val binding: FragmentAddBinding
        get() = _binding ?: throw RuntimeException("FragmentAddBinding = null")

    private lateinit var mImageUri: Uri
    private lateinit var onDataTransmission: OnDataTransmission
    private lateinit var onTransition: OnTransition

    private val viewModel: SendPostViewModel by viewModels()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnDataTransmission)
            onDataTransmission = context
        if (context is OnTransition)
            onTransition = context
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddBinding.inflate(inflater, container, false)
        return binding.root
    }

    interface OnDataTransmission {
        fun onDataTransmission(onResCamera: OnResultCamera)
    }

    interface OnTransition {
        fun onTransition()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val resCamera = object : OnResultCamera {
            override fun onResultCamera(imageUri: Uri) {
                mImageUri = imageUri

                Glide.with(context!!)
                    .load(imageUri)
                    .centerCrop()
                    .into(binding.postImage)
            }
        }

        onDataTransmission.onDataTransmission(resCamera)

        setClickListeners()
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.okSendPost.observe(viewLifecycleOwner) {
            binding.progressBar.visibility = View.GONE
            onTransition.onTransition()
        }
    }

    private fun setClickListeners() {
        binding.sendBtn.setOnClickListener {
            if (binding.captionInput.text.toString().isNotEmpty()) {
                viewModel.sendPost(mImageUri, binding.captionInput.text.toString())
                binding.progressBar.visibility = View.VISIBLE
                /*CoroutineScope(Dispatchers.IO).launch {
                    test()
                }
                onTransition.onTransition()*/
            }
        }
    }

/*    suspend fun test() {
        delay(5000)
        lifecycleScope.launch(Dispatchers.Main) {
            viewModel.okSendPost.observe(viewLifecycleOwner) {
                binding.progressBar.visibility = View.GONE
                onTransition.onTransition()
            }
        }
    }*/
}