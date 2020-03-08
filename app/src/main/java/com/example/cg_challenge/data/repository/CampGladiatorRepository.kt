package com.example.cg_challenge.data.repository

import com.example.cg_challenge.data.network.CampGladiatorInterface
import com.example.cg_challenge.data.network.models.ClassLocationsResponse

class CampGladiatorRepository (private val apiInterface: CampGladiatorInterface) : BaseRepository() {

    suspend fun getNearByClassLocationsResponse(lat : String, long : String, rad : String): ClassLocationsResponse? {
        return safeApiCall(
            //await the result of deferred type
            call = { apiInterface.getClassLocationsAsync(lat, long, rad).await() },
            error = "Error fetching locations"
            //convert to mutable list
        )
    }

}