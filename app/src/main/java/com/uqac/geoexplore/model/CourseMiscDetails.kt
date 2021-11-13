package com.uqac.geoexplore.model

import java.util.*

class CourseMiscDetails {
    lateinit var creator : User
    lateinit var publicationDate : Date
    var rating : Int = 0
    var difficulty : Int = 0
    lateinit var description : String
}