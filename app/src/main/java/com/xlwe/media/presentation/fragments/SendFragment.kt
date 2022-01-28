package com.xlwe.media.presentation.fragments

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.xlwe.media.R
import com.xlwe.media.databinding.DialogDeleteBinding
import com.xlwe.media.databinding.FragmentSendBinding
import com.xlwe.media.domain.model.Message
import com.xlwe.media.presentation.adapters.messageList.MessageListAdapter
import com.xlwe.media.presentation.viewmodels.SendViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SendFragment : Fragment(), TextWatcher {
    private var _binding: FragmentSendBinding? = null
    private val binding: FragmentSendBinding
        get() = _binding ?: throw RuntimeException("FragmentSendBinding == null")

    private val args: SendFragmentArgs by navArgs()
    private val viewModel: SendViewModel by viewModels()
    private lateinit var messageListAdapter: MessageListAdapter
    private lateinit var onCloseFragment: OnClose

    private var message: Message? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is OnClose)
            onCloseFragment = context
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSendBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setListeners()

        setupRecyclerView()

        viewModel.getMessageList(args.user.id)
        observeViewModel()
    }

    private fun setListeners() {
        binding.message.addTextChangedListener(this)

        binding.btnSend.setOnClickListener {
            val messageEditText = binding.message.text.toString()
            val message = Message(messageEditText)

            if (messageEditText.isNotEmpty()) {
                binding.message.setText("")
                viewModel.sendMessage(message, args.user.id)
            }
        }

        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().popBackStack()
                onCloseFragment.onClose()
            }
        }

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
    }

    private fun setupRecyclerView() {
        messageListAdapter = MessageListAdapter()
        messageListAdapter.onMessageItemLongClickListener = { message ->
            dialogBuilder(message)
        }

        binding.messageList.adapter = messageListAdapter
        binding.messageList.itemAnimator = null
    }

    private fun dialogBuilder(message: Message) {
        this.message = message
        /*val dialogAnim = AnimationUtils.loadAnimation(context, R.anim.dialog_anim)
        binding.deleteMessage.startAnimation(dialogAnim)
        binding.deleteMessage.visibility = View.VISIBLE*/

        val dialog = Dialog(requireActivity(), R.style.dialogstyle)
        dialog.setContentView(R.layout.dialog_delete)
        dialog.show()

        dialog.findViewById<Button>(R.id.btnNo).setOnClickListener {
            dialog.dismiss()
        }

        dialog.findViewById<Button>(R.id.btnOk).setOnClickListener {
            dialog.dismiss()
            viewModel.deleteMessage(message, args.user.id)
        }

        /*MaterialAlertDialogBuilder(requireContext())
            .setTitle("Удаление сообщения")
            .setMessage("Вы действительно хотите удалить это сообщение?")
            .setNegativeButton("Нет") { dialog, which -> }
            .setPositiveButton("Да") { dialog, which ->
                viewModel.deleteMessage(message, args.user.id)
            }
            .show()*/
    }

    private fun observeViewModel() {
        viewModel.messageList.observe(viewLifecycleOwner) {
            Log.d("SendFragment", it.toString())
            messageListAdapter.submitList(it)
        }
    }

    interface OnClose {
        fun onClose()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        if (binding.message.text.toString().isNotEmpty())
            binding.btnSend.visibility = View.VISIBLE
        else
            binding.btnSend.visibility = View.GONE
    }

    override fun afterTextChanged(s: Editable?) {}
}