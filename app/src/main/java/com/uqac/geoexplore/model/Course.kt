package com.uqac.geoexplore.model

class Course {
    private lateinit var id : String
    private lateinit var name : String
    private lateinit var location : String
    private lateinit var groups : Group
    private lateinit var checkPoints : ArrayList<CheckPoint>
    private lateinit var feed : Feed
    private lateinit var miscInfo : CourseMiscDetails


}