package com.uqac.geoexplore

import android.content.ContentValues.TAG
import android.util.Log
import com.google.firebase.ktx.Firebase
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.uqac.geoexplore.model.User
import com.google.android.gms.tasks.Tasks;
import kotlinx.coroutines.tasks.await

class Functions {
    companion object {
        suspend fun getUserFromUid(uidToGet: String): User? {
            val db = Firebase.firestore
            val docRef = db.collection("User").document(uidToGet)
            return try {
                val documentSnapshot = docRef.get().await()
                documentSnapshot.toObject<User>()
            } catch (e: Exception) {
                null
            }

        }
    }

}