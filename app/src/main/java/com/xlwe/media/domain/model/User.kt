package com.xlwe.media.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    var id: String = "",
    var name: String = "",
    var message: String = ""
): Parcelable