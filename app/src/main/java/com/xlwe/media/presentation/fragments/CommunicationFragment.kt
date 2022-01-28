package com.xlwe.media.presentation.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.xlwe.media.databinding.FragmentCommunicationBinding
import com.xlwe.media.domain.model.User
import com.xlwe.media.presentation.adapters.userList.UserListAdapter
import com.xlwe.media.presentation.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CommunicationFragment : Fragment() {
    private var _binding: FragmentCommunicationBinding? = null
    private val binding: FragmentCommunicationBinding
        get() = _binding ?: throw RuntimeException("FragmentCommunicationBinding == null")

    private val viewModel: MainViewModel by viewModels()

    private lateinit var userListAdapter: UserListAdapter

    private lateinit var onClickUser: OnClickUser

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is OnClickUser)
            onClickUser = context
        else
            throw RuntimeException("Activity must implement OnClickUser")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCommunicationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()

        viewModel.userList.observe(viewLifecycleOwner) {
            userListAdapter.submitList(it)
        }
    }

    data class tttt(val name: String)

    private fun setupRecyclerView() {
        userListAdapter = UserListAdapter()
        binding.userList.adapter = userListAdapter

        userListAdapter.onUserItemClickListener = {
            onClickUser.onClickUser(it)
            val direction = CommunicationFragmentDirections.actionCommunicationFragmentToSendFragment(it)
            findNavController().navigate(direction)
        }
    }

    interface OnClickUser {
        fun onClickUser(user: User)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}