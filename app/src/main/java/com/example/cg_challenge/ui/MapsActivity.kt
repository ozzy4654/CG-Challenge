package com.example.cg_challenge.ui

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.cg_challenge.R
import com.example.cg_challenge.data.network.models.PlaceData
import com.example.cg_challenge.utils.createNetworkCallback
import com.example.cg_challenge.utils.networkRequest
import com.google.android.gms.common.api.Status
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.widget.AutocompleteSupportFragment
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener
import kotlinx.android.synthetic.main.activity_maps.*


class MapsActivity : AppCompatActivity(), OnMapReadyCallback {
    private lateinit var mMap: GoogleMap
    private lateinit var mFusedLocationProviderClient: FusedLocationProviderClient
    private lateinit var classLocationsViewModel: ClassLocationsViewModel
    private lateinit var classLocationsObserver : Observer<MutableList<PlaceData>>
    private lateinit var autocompleteFragment : AutocompleteSupportFragment

    private val PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 3
    private var mLocationPermissionGranted = false
    private var mLastKnownLocation: Location? = null
    private var mDefaultLocation = LatLng(41.881832, -87.623177)
    private var networkCallback: ConnectivityManager.NetworkCallback? = null
    private var cm: ConnectivityManager? = null
    private var currentLocation: LatLng? = null
    private var isLaunch = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)

        search_progress_indicator.visibility =  View.VISIBLE

        setUpNetworkWatcher()

        // Initialize Places.
        Places.initialize(applicationContext, this.getString(R.string.google_maps_key))


        // Construct a FusedLocationProviderClient.
        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        classLocationsViewModel = ViewModelProvider(this)
            .get(ClassLocationsViewModel::class.java)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment

        mapFragment.getMapAsync(this)

        // Initialize the AutocompleteSupportFragment.
        autocompleteFragment = (supportFragmentManager
            .findFragmentById(R.id.autocomplete_fragment) as AutocompleteSupportFragment)


        if (savedInstanceState == null) {

            classLocationsViewModel.getNearByClassLocations(
                currentLocation?.latitude.toString(),
                currentLocation?.longitude.toString(),
                "25"
            )
        } else {
            isLaunch = false
        }

    }


    private fun setUpNetworkWatcher() {
        //network checker from Android Developers site
        networkCallback =
            createNetworkCallback(this)
        cm = getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        cm!!.registerNetworkCallback(networkRequest, networkCallback)
    }

    override fun onStop() {
        super.onStop()
        cm?.unregisterNetworkCallback(networkCallback)
    }

    private fun getLocationPermission(mMap: GoogleMap) {
        /*
         * Request location permission, so that we can get the location of the
         * device. The result of the permission request is handled by a callback,
         * onRequestPermissionsResult.
         */
        if (ContextCompat.checkSelfPermission(
                this.applicationContext,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            mLocationPermissionGranted = true
            mMap.isMyLocationEnabled = true

        } else {
            ActivityCompat.requestPermissions(
                this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION
            )
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String?>,
        grantResults: IntArray
    ) {
        mLocationPermissionGranted = false
        when (requestCode) {
            PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION -> {

                // If request is cancelled, the result arrays are empty.
                if (grantResults.isNotEmpty()
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED
                ) {
                    mLocationPermissionGranted = true
                    mMap.isMyLocationEnabled = true

                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when (requestCode) {
            PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION -> {
                if (mLocationPermissionGranted) {
                    mLocationPermissionGranted = true
                    mMap.isMyLocationEnabled = true
                } else {
                    getLocationPermission(mMap)
                }
            }
        }
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        search_progress_indicator.visibility = View.INVISIBLE
        mMap = googleMap

        setPlaceObserver()
        setPlaceSelectionListener()

        //checks for permissions
        updateLocationUI()

        // on app launch we check for local locations
        if (isLaunch)
            getDeviceLocation()

        if (classLocationsViewModel.lastSearch != null)
            setMarkers(classLocationsViewModel.classLocationsLiveData.value!!)

    }

    private fun setPlaceSelectionListener() {
        // Specify the types of place data to return.
        autocompleteFragment.setPlaceFields(
            listOf(
                Place.Field.ID,
                Place.Field.NAME,
                Place.Field.LAT_LNG
            )
        )

        // Set up a PlaceSelectionListener to handle the response.
        autocompleteFragment.setOnPlaceSelectedListener(object : PlaceSelectionListener {
            override fun onPlaceSelected(place: Place) {
                search_progress_indicator.visibility = View.VISIBLE
                classLocationsViewModel.lastSearch = place.latLng
                // clear the map markers for performance.
                mMap.clear()

                //todo  create a function or view model to strip this out of the activity
                classLocationsViewModel.getNearByClassLocations(
                    place.latLng?.latitude.toString(),
                    place.latLng?.longitude.toString(),
                    "20"
                )


                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(place.latLng, 12f))
//                Log.i(FragmentActivity.TAG, "Place: " + place.name + ", " + place.id)
            }

            override fun onError(status: Status) {
                // TODO: Handle the error.
//                Log.i(FragmentActivity.TAG, "An error occurred: $status")
            }
        })

    }

    private fun setMarkers(newList : MutableList<PlaceData>) {
        newList.forEach {
            mMap.addMarker(
                MarkerOptions()
                    .position(
                        LatLng(
                            it.placeLatitude.toDouble(),
                            it.placeLongitude.toDouble()
                        )
                    )
                    .title(it.placeName)
            )
        }
        search_progress_indicator.visibility = View.INVISIBLE
    }

    private fun setPlaceObserver () {
        // Create the observer which updates the UI.
        classLocationsObserver = Observer<MutableList<PlaceData>> { newList ->
            search_progress_indicator.visibility = View.VISIBLE
            run {
                //scoreValue.text = newScore.toString()
                if (newList.isNotEmpty()) {
                    newList.forEach {
                        mMap.addMarker(
                            MarkerOptions()
                                .position(
                                    LatLng(
                                        it.placeLatitude.toDouble(),
                                        it.placeLongitude.toDouble()
                                    )
                                )
                                .title(it.placeName)
                        )
                    }




                } else {
                    Toast.makeText(
                        this@MapsActivity,
                        getString(R.string.toast_no_classes),
                        Toast.LENGTH_LONG
                    ).show()

                }

                search_progress_indicator.visibility = View.INVISIBLE
            }
        }
        // Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.
        classLocationsViewModel.classLocationsLiveData.observe(
            this@MapsActivity,
            classLocationsObserver
        )
    }

    private fun updateLocationUI() {
        if (mMap == null) {
            return
        }
        try {
            if (mLocationPermissionGranted) {
                mMap.isMyLocationEnabled = true
                mMap.uiSettings.isMyLocationButtonEnabled = true
            } else {
                mMap.isMyLocationEnabled = false
                mMap.uiSettings.isMyLocationButtonEnabled = false
                mLastKnownLocation = null
                getLocationPermission(mMap)
            }
        } catch (e: SecurityException) {
            Log.e("Exception: %s", e.message)
        }
    }


    private fun getDeviceLocation() {
        /*
     * Get the best and most recent location of the device, which may be null in rare
     * cases when a location is not available.
     */

        try {
            if (mLocationPermissionGranted) {
                mFusedLocationProviderClient.lastLocation.addOnSuccessListener(this) { location ->
                    // Got last known location. In some rare situations this can be null.
                    if (location != null) {
                        mLastKnownLocation = location
                        currentLocation = LatLng(mLastKnownLocation!!.latitude, mLastKnownLocation!!.longitude)
                    } else {
                        currentLocation = mDefaultLocation
                    }
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLocation, 12f))
                    isLaunch = false

                }
            }

        } catch (e: SecurityException) {
            Log.e("Exception: %s", e.message)
        }

    }


}
