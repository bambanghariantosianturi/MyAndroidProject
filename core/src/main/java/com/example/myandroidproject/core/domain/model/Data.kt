package com.example.myandroidproject.core.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Data(
    val id: Int,
    val login: String,
    val avatarUrl: String
) : Parcelable