package com.example.final_finaltest

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Store(
    var name: String,
    var phone: String,
    var address: String,
    var rating: Float,
    var imageResId: Int? = null
) : Parcelable
