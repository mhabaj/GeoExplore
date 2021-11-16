package com.uqac.geoexplore.model


class User(var id: String?=null, var shownName: String?=null, var email: String?=null, var friends: List<String>?= null) {


    override fun toString(): String {
        return "User(id=$id, shownName=$shownName, email=$email, friends=$friends)"
    }

}