package com.uqac.geoexplore.model

import com.google.firebase.Timestamp
import java.util.*

class ChatMessage(val text: String, val user: String, val other_user:String, val timestamp: Timestamp) {
    override fun toString(): String {
        return "Message(text=$text, user=$user,user=$other_user, timestamp=$timestamp)"
    }
}