package com.example.cg_challenge.ui

import android.app.Application
import androidx.lifecycle.*
import com.example.cg_challenge.data.network.ApiServiceProvider
import com.example.cg_challenge.data.network.models.PlaceData
import com.example.cg_challenge.data.repository.ClassLocationsRepository
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class ClassLocationsViewModel : ViewModel() {
    //create a new Job
    private val parentJob = Job()

    //create a coroutine context with the job and the dispatcher
    private val coroutineContext : CoroutineContext get() = parentJob + Dispatchers.Default

    //create a coroutine scope with the coroutine context
    private val scope = CoroutineScope(coroutineContext)

    //initialize news repo
//    private val questionsDao : QuestionsDao = AppDatabase.getInstance(application, viewModelScope).questionDao()

    private val classLocationsRepository : ClassLocationsRepository
    //live api data that will be populated as question updates
    val classLocationsLiveData = MutableLiveData<MutableList<PlaceData>>()

//
//    // The ViewModel maintains a reference to the repository to get data.
//    // LiveData gives us updated words when they change.
//    val allClasses: LiveData<List<PlaceData>>

    init {
        // Gets reference to QuestionDao from AppDatabase to construct
        // the correct QuestionRepository.
        // structure here follows google code lab (Word list)
        classLocationsRepository =
            ClassLocationsRepository(ApiServiceProvider.classLocationsService)
//        allClasses = classLocationsRepository.getNearByClassL
    }

    fun getNearByClassLocations(lat : String, long : String, rad : String ) {
        ///launch the coroutine scope
        scope.launch {
            //get latest news from news repo
            val nearByClasses = classLocationsRepository
                .getNearByClassLocationsResponse(lat, long, rad)

            //post the value inside live data
            println("THE LOCATIONSSNSN    ${nearByClasses?.classLocations?.size}   and  ${nearByClasses?.classLocations}")
            classLocationsLiveData.postValue(nearByClasses?.classLocations as MutableList<PlaceData>)

        }
    }
    fun cancelRequests() = coroutineContext.cancel()
}