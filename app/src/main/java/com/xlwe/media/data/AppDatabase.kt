package com.xlwe.media.data

import com.google.firebase.auth.FirebaseAuth

class AppDatabase {
    companion object {
        val auth: FirebaseAuth = FirebaseAuth.getInstance()
    }
}