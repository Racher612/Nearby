package com.project.nearby.geohash.domain

import kotlin.math.*

object GeoHash {
    private const val BASE32 = "0123456789bcdefghjkmnpqrstuvwxyz"
    private val LATITUDE_RANGE = -90.0..90.0
    private val LONGITUDE_RANGE = -180.0..180.0
    private const val EARTH_RADIUS_KM = 6371.0

    fun encode(latitude: Double, longitude: Double, precision: Int = 12): String {
        var latRange = LATITUDE_RANGE
        var lonRange = LONGITUDE_RANGE
        val geohash = StringBuilder()
        var isEven = true
        var bit = 0
        var value = 0

        while (geohash.length < precision) {
            val mid: Double
            if (isEven) {
                mid = (lonRange.start + lonRange.endInclusive) / 2
                if (longitude > mid) {
                    value = value or (1 shl (4 - bit))
                    lonRange = mid..lonRange.endInclusive
                } else {
                    lonRange = lonRange.start..mid
                }
            } else {
                mid = (latRange.start + latRange.endInclusive) / 2
                if (latitude > mid) {
                    value = value or (1 shl (4 - bit))
                    latRange = mid..latRange.endInclusive
                } else {
                    latRange = latRange.start..mid
                }
            }
            isEven = !isEven
            if (bit < 4) {
                bit++
            } else {
                geohash.append(BASE32[value])
                bit = 0
                value = 0
            }
        }
        return geohash.toString()
    }

    fun decode(geohash: String): Pair<ClosedRange<Double>, ClosedRange<Double>> {
        var latRange = LATITUDE_RANGE
        var lonRange = LONGITUDE_RANGE
        var isEven = true

        for (char in geohash) {
            val value = BASE32.indexOf(char)
            for (bit in 4 downTo 0) {
                val mask = 1 shl bit
                if (isEven) {
                    val mid = (lonRange.start + lonRange.endInclusive) / 2
                    if (value and mask != 0) {
                        lonRange = mid..lonRange.endInclusive
                    } else {
                        lonRange = lonRange.start..mid
                    }
                } else {
                    val mid = (latRange.start + latRange.endInclusive) / 2
                    if (value and mask != 0) {
                        latRange = mid..latRange.endInclusive
                    } else {
                        latRange = latRange.start..mid
                    }
                }
                isEven = !isEven
            }
        }
        return latRange to lonRange
    }

    /**
     * Calculates a bounding box for a given radius around a point.
     * @param latitude Latitude in degrees
     * @param longitude Longitude in degrees
     * @param radiusKm Radius in kilometers
     * @return A list of geohashes covering the area
     */
    fun getGeoHashesInRadius(latitude: Double, longitude: Double, radiusKm: Double, precision: Int = 6): List<String> {
        val latDelta = radiusKm / EARTH_RADIUS_KM * (180 / PI) // Latitude delta in degrees
        val lonDelta = radiusKm / (EARTH_RADIUS_KM * cos(latitude * PI / 180)) * (180 / PI) // Longitude delta in degrees

        val latMin = latitude - latDelta
        val latMax = latitude + latDelta
        val lonMin = longitude - lonDelta
        val lonMax = longitude + lonDelta

        val geohashes = mutableSetOf<String>()

        // Step size in degrees (determines granularity)
        val step = 0.1

        var lat = latMin
        while (lat <= latMax) {
            var lon = lonMin
            while (lon <= lonMax) {
                geohashes.add(encode(lat, lon, precision))
                lon += step
            }
            lat += step
        }
        return geohashes.toList()
    }
}