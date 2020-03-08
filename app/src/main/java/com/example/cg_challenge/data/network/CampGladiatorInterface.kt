package com.example.cg_challenge.data.network

import com.example.cg_challenge.data.network.models.ClassLocationsResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CampGladiatorInterface {

    @GET("places/searchbydistance?")
    fun getClassLocationsAsync(
        @Query("lat") lat : String,
        @Query("lon") long : String,
        @Query("radius") radius : String
    ): Deferred<Response<ClassLocationsResponse>>
}