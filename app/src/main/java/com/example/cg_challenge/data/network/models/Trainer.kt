package com.example.cg_challenge.data.network.models


import com.google.gson.annotations.SerializedName

data class Trainer(
    @SerializedName("adminUserEmail")
    val adminUserEmail: String,
    @SerializedName("adminUserFirstname")
    val adminUserFirstname: String,
    @SerializedName("adminUserID")
    val adminUserID: String,
    @SerializedName("adminUserLastname")
    val adminUserLastname: String,
    @SerializedName("adminUserPhone")
    val adminUserPhone: String,
    @SerializedName("adminUserPhoto")
    val adminUserPhoto: String,
    @SerializedName("locationSplitPerc")
    val locationSplitPerc: String
)