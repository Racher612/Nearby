package com.project.nearby.main.pres

import android.app.Application
import android.widget.Toast
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.nearby.R
import com.project.nearby.geohash.data.LocationHelper
import com.project.nearby.geohash.data.RetrofitGeoClient
import com.project.nearby.geohash.domain.GeoHash
import com.project.nearby.main.data.RetrofitClient
import com.project.nearby.retrofit.models.concert.Concert
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val app: Application
) : ViewModel () {
    private val _loadkey = mutableStateOf(false)
    val loadkey : State<Boolean> = _loadkey
    private val _popUp = mutableStateOf(false)
    var popUp = _popUp

    private val _searchRadius = mutableStateOf("")
    var searchRadius = _searchRadius

    private val _concerts = mutableStateOf<Concert?>(null)
    val concerts : State<Concert?> = _concerts

    private val _loading = mutableStateOf(false)
    val loading : State<Boolean> = _loading

    private fun loadConcerts(){
        _loading.value = true
        viewModelScope.launch {
            _concerts.value = RetrofitClient.getAllConcerts().body()
            _loading.value = false
        }
    }

    fun loadConcertsByGeo(){
        val locationHelper = LocationHelper(app.applicationContext)
        locationHelper.getCurrentLocation { location ->
            location?.let {
                val latitude = it.latitude
                val longitude = it.longitude
                val geoHash = GeoHash.getGeoHashesInRadius(
                    latitude,
                    longitude,
                    _searchRadius.value.toDouble()
                )
                _loading.value = true
                viewModelScope.launch {
                    _concerts.value = null
                    _concerts.value = RetrofitGeoClient.getConcertsByGeo(geoHash[0]).body()
                    _loading.value = false
                }
            } ?: run {
                Toast.makeText(app.applicationContext,
                    app.applicationContext.getString(R.string.could_not_get_geolocation), Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun openWindow(){
        _popUp.value = !popUp.value
    }

    fun readRadius(radius : String){
        _searchRadius.value = radius
    }

    fun loadConcertsFromPopUp(){
        _loadkey.value = true
    }



    init {
        loadConcerts()
    }

}