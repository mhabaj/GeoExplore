package com.uqac.geoexplore.model

import android.text.format.DateFormat

class Comment {
    private lateinit  var author : User
    private lateinit  var date : DateFormat
    private lateinit  var textContent : String
    private lateinit  var photos : String
}