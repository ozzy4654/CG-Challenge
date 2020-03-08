package com.example.cg_challenge.data.network

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object CampGladiatorApiServiceProvider {

    /**
     * Instance of the [CampGladiatorInterface] service that is ready to use.
     */
    val CLASS_CAMP_GLADIATOR_SERVICE: CampGladiatorInterface

    init {
        val client = createOkHttpClient()
        CLASS_CAMP_GLADIATOR_SERVICE = provideStackExchangeRetrofit(client, provideGson())
            .create(CampGladiatorInterface::class.java)
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