package com.project.nearby.geohash.data

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import android.widget.Toast
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.tasks.OnCompleteListener
import com.project.nearby.R


class LocationHelper(private val context: Context) {
    private val fusedLocationClient: FusedLocationProviderClient =
        LocationServices.getFusedLocationProviderClient(context)

    @SuppressLint("MissingPermission")
    fun getCurrentLocation(onLocationReceived: (Location?) -> Unit) {
        fusedLocationClient.lastLocation.addOnCompleteListener(OnCompleteListener { task ->
            if (task.isSuccessful && task.result != null) {
                onLocationReceived(task.result)
            } else {
                Toast.makeText(
                    context,
                    context.getString(R.string.no_permission_for_location),
                    Toast.LENGTH_SHORT
                ).show()
                onLocationReceived(null)
            }
        })
    }
}