package com.example.cg_challenge.data.network

sealed class CampGladiatorOutput <out T : Any>{
    data class Success<out T : Any>(val data : T) : CampGladiatorOutput<T>()
    data class Error(val exception: Exception)  : CampGladiatorOutput<Nothing>()
}