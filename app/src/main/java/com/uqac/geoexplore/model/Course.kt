package com.uqac.geoexplore.model



import com.google.android.gms.maps.model.LatLng
import com.google.firebase.firestore.GeoPoint

data class Course (var name: String ?= null,
                   var miscInfo:CourseMiscDetails ?= null,
                   var location: GeoPoint ?= null,
                   var group: Group ?= null,
                   var checkPoint: CheckPoint ?= null,
                   var feed: Feed ?= null) {


    /* private lateinit var id : String
    private lateinit var name : String
    private lateinit var location : String
    private lateinit var groups : ArrayList<Group>
    private lateinit var checkPoints : ArrayList<CheckPoint>
    private lateinit var feed : Feed
    private lateinit var miscInfo : CourseMiscDetails

import com.google.android.gms.maps.model.LatLng
import com.google.firebase.firestore.GeoPoint

class Course {
    private lateinit var id : String
    lateinit var name : String
    lateinit var location : GeoPoint
    private lateinit var groups : Group
    private lateinit var checkPoints : ArrayList<CheckPoint>
    private lateinit var feed : Feed
    lateinit var miscInfo : CourseMiscDetails

*/
}