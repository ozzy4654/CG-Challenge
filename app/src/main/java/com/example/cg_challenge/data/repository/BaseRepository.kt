package com.example.cg_challenge.data.repository

import android.util.Log
import com.example.cg_challenge.data.network.CampGladiatorOutput
import retrofit2.Response
import java.io.IOException

open class BaseRepository {

    suspend fun <T : Any> safeApiCall(call : suspend()-> Response<T>, error : String) :  T?{
        val result = classLocationsApiOutput(call, error)
        var data : T? = null

        when(result){
            is CampGladiatorOutput.Success ->
                data = result.data
            is CampGladiatorOutput.Error -> Log.e("Error", "The $error and the ${result.exception}")
        }
        return data

    }
    private suspend fun<T : Any> classLocationsApiOutput(call: suspend()-> Response<T>, error: String) : CampGladiatorOutput<T> {
        val response = call.invoke()

        return if (response.isSuccessful)
            CampGladiatorOutput.Success(response.body()!!)
        else
            CampGladiatorOutput.Error(IOException("OOps .. Something went wrong due to  $error"))
    }

}