package com.uqac.geoexplore.model


class User(var id: String, var shownName: String, var email: String , var friends : List<String>) {

    override fun toString(): String {
        return "User(id='$id', shownName='$shownName', email='$email',friends=$friends)"
    }




}