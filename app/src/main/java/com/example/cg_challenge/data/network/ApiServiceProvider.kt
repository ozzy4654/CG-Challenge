package com.example.cg_challenge.data.network

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiServiceProvider {

    /**
     * Instance of the [LocationsInterface] service that is ready to use.
     */
    val classLocationsService: LocationsInterface

    init {
        val client = createOkHttpClient()
        classLocationsService = provideStackExchangeRetrofit(client, provideGson())
            .create(LocationsInterface::class.java)
    }

    private fun createOkHttpClient(): OkHttpClient {
        return OkHttpClient
            .Builder()
            .build()
    }

    private fun provideStackExchangeRetrofit(client: OkHttpClient, gson: Gson) = Retrofit.Builder()
        .client(client)
        .baseUrl("https://stagingapi.campgladiator.com/api/v2/")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()

    private fun provideGson() = GsonBuilder().create()

}