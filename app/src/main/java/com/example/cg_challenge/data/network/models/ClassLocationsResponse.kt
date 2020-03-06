package com.example.cg_challenge.data.network.models


import com.google.gson.annotations.SerializedName

data class ClassLocationsResponse(
    @SerializedName("data")
    val classLocations: List<PlaceData>,
    @SerializedName("message")
    val message: String,
    @SerializedName("success")
    val success: Boolean
)