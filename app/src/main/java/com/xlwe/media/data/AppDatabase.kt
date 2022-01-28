package com.xlwe.media.data

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

class AppDatabase {
    companion object {
        val auth: FirebaseAuth = FirebaseAuth.getInstance()
        val db: DatabaseReference = FirebaseDatabase.getInstance().reference
        val storage: StorageReference = FirebaseStorage.getInstance().reference
    }
}