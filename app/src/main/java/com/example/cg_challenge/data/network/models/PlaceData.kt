package com.example.cg_challenge.data.network.models


import com.google.gson.annotations.SerializedName

data class PlaceData(
    @SerializedName("placeLatitude")
    val placeLatitude: String,
    @SerializedName("placeLongitude")
    val placeLongitude: String,
    @SerializedName("placeName")
    val placeName: String//,
//    @SerializedName("distance")
//    val distance: String,
//    @SerializedName("location")
//    val location: List<Location>,
//    @SerializedName("placeActive")
//    val placeActive: String,
//    @SerializedName("placeAdditionalInfo")
//    val placeAdditionalInfo: String,
//    @SerializedName("placeAddress1")
//    val placeAddress1: String,
//    @SerializedName("placeAddress2")
//    val placeAddress2: String?,
//    @SerializedName("placeCity")
//    val placeCity: String,
//    @SerializedName("placeCountry")
//    val placeCountry: String,
//    @SerializedName("placeDesc")
//    val placeDesc: String,
//    @SerializedName("placeID")
//    val placeID: String,
//    @SerializedName("placeState")
//    val placeState: String,
//    @SerializedName("placeZipcode")
//    val placeZipcode: String,
//    @SerializedName("regionID")
//    val regionID: String,
//    @SerializedName("subRegionID")
//    val subRegionID: String
)