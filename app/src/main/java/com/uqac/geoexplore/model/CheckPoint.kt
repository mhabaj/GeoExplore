package com.uqac.geoexplore.model

class CheckPoint(var id: String ?= null, var index: Int ?= null, var textcontent: String ?= null) {

    override fun toString(): String {
        return "CheckPoint(id='$id', textcontent='$textcontent')"
    }
}