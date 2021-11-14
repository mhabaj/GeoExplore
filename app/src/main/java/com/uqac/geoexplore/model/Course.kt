package com.uqac.geoexplore.model

import com.google.android.gms.maps.model.LatLng

class Course {
    private lateinit var id : String
    lateinit var name : String
    lateinit var location : LatLng
    private lateinit var groups : Group
    private lateinit var checkPoints : ArrayList<CheckPoint>
    private lateinit var feed : Feed
    lateinit var miscInfo : CourseMiscDetails


}