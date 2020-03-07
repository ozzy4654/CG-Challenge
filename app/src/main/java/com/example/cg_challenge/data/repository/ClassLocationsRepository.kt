package com.example.cg_challenge.data.repository

import com.example.cg_challenge.data.network.LocationsInterface
import com.example.cg_challenge.data.network.models.ClassLocationsResponse

class ClassLocationsRepository (private val apiInterface: LocationsInterface) : BaseRepository() {

    suspend fun getNearByClassLocationsResponse(lat : String, long : String, rad : String): ClassLocationsResponse? {
        return safeApiCall(
            //await the result of deferred type
            call = { apiInterface.getClassLocationsAsync(lat, long, rad).await() },
            error = "Error fetching locations"
            //convert to mutable list
        )
    }

}