package com.example.cg_challenge.data.network.models


import com.google.gson.annotations.SerializedName

data class Location(
    @SerializedName("approvedDate")
    val approvedDate: Any?,
    @SerializedName("ID")
    val iD: String,
    @SerializedName("isApproved")
    val isApproved: String,
    @SerializedName("lastSubmissionDate")
    val lastSubmissionDate: Any?,
    @SerializedName("locationActive")
    val locationActive: String,
    @SerializedName("locationAddress1")
    val locationAddress1: Any?,
    @SerializedName("locationAddress2")
    val locationAddress2: Any?,
    @SerializedName("locationAge")
    val locationAge: String,
    @SerializedName("locationAlternate")
    val locationAlternate: String,
    @SerializedName("locationAvgRating")
    val locationAvgRating: Any?,
    @SerializedName("locationCity")
    val locationCity: Any?,
    @SerializedName("locationDateEntered")
    val locationDateEntered: String,
    @SerializedName("locationDay")
    val locationDay: String,
    @SerializedName("locationDesc")
    val locationDesc: String,
    @SerializedName("locationEndDate")
    val locationEndDate: Any?,
    @SerializedName("locationEndHour")
    val locationEndHour: String,
    @SerializedName("locationEndMeetDate")
    val locationEndMeetDate: Any?,
    @SerializedName("locationEndMeridiem")
    val locationEndMeridiem: String,
    @SerializedName("locationEndMin")
    val locationEndMin: String,
    @SerializedName("locationEndRegistrationDate")
    val locationEndRegistrationDate: Any?,
    @SerializedName("locationFullAddress")
    val locationFullAddress: String,
    @SerializedName("locationFullDesc")
    val locationFullDesc: Any?,
    @SerializedName("locationID")
    val locationID: String,
    @SerializedName("locationInactiveDate")
    val locationInactiveDate: Any?,
    @SerializedName("locationIsVisible")
    val locationIsVisible: String,
    @SerializedName("locationLatitude")
    val locationLatitude: String,
    @SerializedName("locationLongitude")
    val locationLongitude: String,
    @SerializedName("locationName")
    val locationName: String,
    @SerializedName("locationOriginalStartDate")
    val locationOriginalStartDate: String,
    @SerializedName("locationStartDate")
    val locationStartDate: String,
    @SerializedName("locationStartHour")
    val locationStartHour: String,
    @SerializedName("locationStartMeetDate")
    val locationStartMeetDate: String,
    @SerializedName("locationStartMeridiem")
    val locationStartMeridiem: String,
    @SerializedName("locationStartMin")
    val locationStartMin: String,
    @SerializedName("locationStartRegistrationDate")
    val locationStartRegistrationDate: String,
    @SerializedName("locationTime")
    val locationTime: String,
    @SerializedName("locationZipcode")
    val locationZipcode: Any?,
    @SerializedName("originalSubmissionDate")
    val originalSubmissionDate: Any?,
    @SerializedName("parentLocationID")
    val parentLocationID: String,
    @SerializedName("placeID")
    val placeID: String,
    @SerializedName("regionID")
    val regionID: String,
    @SerializedName("rootParentLocationID")
    val rootParentLocationID: String,
    @SerializedName("trainerID")
    val trainerID: String,
    @SerializedName("trainers")
    val trainers: List<Trainer>,
    @SerializedName("underPerformingCount")
    val underPerformingCount: String
)