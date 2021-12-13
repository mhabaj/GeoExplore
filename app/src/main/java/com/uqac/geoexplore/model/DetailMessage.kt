package com.uqac.geoexplore.model

import com.google.firebase.Timestamp
import java.util.*

class DetailMessage(val text: String, val user: String,val other_user: String,val timestamp: Timestamp, val id: String) {
    override fun toString(): String {
        return "Message(text=$text, user=$user, id=$id,other_user=$other_user)"
    }
}