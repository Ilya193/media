package com.xlwe.media.presentation.adapters.userList

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.xlwe.media.databinding.UserItemLayoutBinding
import com.xlwe.media.domain.model.User

class UserListAdapter: ListAdapter<User, UserListAdapter.UserItemViewHolder>(UserListDiffCallback()) {
    class UserItemViewHolder(val binding: UserItemLayoutBinding): RecyclerView.ViewHolder(binding.root)

    var onUserItemClickListener: ((User) -> Unit)? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): UserItemViewHolder {
        return UserItemViewHolder(UserItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: UserItemViewHolder, position: Int) {
        val user = getItem(position)
        with(holder) {
            val firebaseUser = FirebaseAuth.getInstance().currentUser!!.uid
            var currentUser: User? = null
            FirebaseDatabase.getInstance().reference.child("users/$firebaseUser")
                .addValueEventListener(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        currentUser = snapshot.getValue(User::class.java)!!

                        if (user.id == currentUser?.message) {
                            binding.nRead.visibility = View.VISIBLE
                        }

                        Log.d("Adapterr", currentUser.toString())
                    }

                    override fun onCancelled(error: DatabaseError) {}
                })

            binding.tvName.text = user.name
            binding.root.setOnClickListener {
                onUserItemClickListener?.invoke(user)
            }
        }
    }
}