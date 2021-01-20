package com.aim.leantechtest.data.model

import com.google.gson.annotations.SerializedName

class User (
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("login")
    val name: String = "",
    @SerializedName("avatar_url")
    val avatar: String = ""
)
