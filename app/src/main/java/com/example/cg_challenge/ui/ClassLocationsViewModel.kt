package com.example.cg_challenge.ui

import androidx.lifecycle.*
import com.example.cg_challenge.data.network.CampGladiatorApiServiceProvider
import com.example.cg_challenge.data.network.models.PlaceData
import com.example.cg_challenge.data.repository.CampGladiatorRepository
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class ClassLocationsViewModel : ViewModel() {
    //create a new Job
    private val parentJob = Job()
    //create a coroutine context with the job and the dispatcher
    private val coroutineContext : CoroutineContext get() = parentJob + Dispatchers.Default
    //create a coroutine scope with the coroutine context
    private val scope = CoroutineScope(coroutineContext)

    private val campGladiatorRepository : CampGladiatorRepository

    //live api data that will be populated as location changes in search
    val classLocationsLiveData = MutableLiveData<MutableList<PlaceData>>()

    var lastSearch : LatLng? = null

    init {
        campGladiatorRepository = CampGladiatorRepository(CampGladiatorApiServiceProvider.CLASS_CAMP_GLADIATOR_SERVICE)
    }

    fun getNearByClassLocations(lat : String, long : String, rad : String ) {
        ///launch the coroutine scope
        scope.launch {
            //get latest class locations from CG repo
            val nearByClasses = campGladiatorRepository
                .getNearByClassLocationsResponse(lat, long, rad)

            //post the value inside live data
            classLocationsLiveData.postValue(nearByClasses?.classLocations as MutableList<PlaceData>)

        }
    }


    fun cancelRequests() = coroutineContext.cancel()
}