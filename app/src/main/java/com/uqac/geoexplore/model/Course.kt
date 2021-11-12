package com.uqac.geoexplore.model

import android.location.Geocoder
import com.google.android.gms.maps.model.LatLng

class Course (var name : String, var location : String, var miscInfo : CourseMiscDetails) {
    private lateinit var id : String
    private lateinit var groups : Group
    private var checkPoints : ArrayList<CheckPoint> = ArrayList()
    private lateinit var feed : Feed
}