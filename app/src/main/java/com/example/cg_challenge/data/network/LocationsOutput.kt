package com.example.cg_challenge.data.network

sealed class LocationsOutput <out T : Any>{
    data class Success<out T : Any>(val data : T) : LocationsOutput<T>()
    data class Error(val exception: Exception)  : LocationsOutput<Nothing>()
}