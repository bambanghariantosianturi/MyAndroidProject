package com.example.myandroidproject.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class DataResponse(
    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("login")
    val login: String,

    @field:SerializedName("avatar_url")
    val avatarUrl: String
)