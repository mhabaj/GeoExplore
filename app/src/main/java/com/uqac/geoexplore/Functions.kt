package com.uqac.geoexplore

import android.content.ContentValues.TAG
import android.util.Log
import com.google.firebase.ktx.Firebase
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.uqac.geoexplore.model.User
import com.google.android.gms.tasks.Tasks;
import com.uqac.geoexplore.model.Course
import com.uqac.geoexplore.model.Group
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

        suspend fun getCourseFromId(IdToGet: String): Course? {
            val db = Firebase.firestore
            val docRef = db.collection("Course").document(IdToGet)
            return try {
                val documentSnapshot = docRef.get().await()
                documentSnapshot.toObject<Course>()
            } catch (e: Exception) {
                null
            }

        }

        suspend fun retrieveUid(usersInGroup: ArrayList<String>): Group? {
            val db = Firebase.firestore
            val groupToAdd = Group(ArrayList())

            return try {
                for (userInGroup in usersInGroup) {
                    db.collection("User").whereEqualTo("shownName", userInGroup).get().addOnSuccessListener {
                            users ->
                        for (user in users) {
                            val userID = user.toObject<User>().id

                            println("Adding uid to group : $userID")
                            groupToAdd.participants?.add(userID!!)
                        }
                    }.await()
                }
                return groupToAdd
            } catch (e: java.lang.Exception) {
                null
            }
        }
    }

}