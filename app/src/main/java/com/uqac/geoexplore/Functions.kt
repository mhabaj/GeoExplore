package com.uqac.geoexplore

import android.annotation.SuppressLint
import android.content.Context
import android.location.*
import android.util.Log
import com.google.firebase.ktx.Firebase
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.uqac.geoexplore.model.User
import com.google.firebase.firestore.*
import com.uqac.geoexplore.model.Course
import kotlinx.coroutines.tasks.await
import android.location.LocationManager
import androidx.core.content.ContextCompat

import androidx.core.content.ContextCompat.getSystemService
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationServices


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

        @SuppressLint("MissingPermission")
        suspend fun sortCourses(
            difficulty: String? = null,
            note: String? = null,
            distance: String? = null
        ): ArrayList<Course>? {

            var courseArraylist: ArrayList<Course> = ArrayList()

            //On recup les courses (A AMELIORER: Recuperer que les courses qui nous interessent

            if (!difficulty.isNullOrEmpty()) {

                val db = Firebase.firestore

                db.collection("Course").whereEqualTo("miscInfo.difficulty", difficulty.toInt())
                    .addSnapshotListener(object : EventListener<QuerySnapshot> {
                        override fun onEvent(
                            value: QuerySnapshot?,
                            error: FirebaseFirestoreException?
                        ) {
                            if (error != null) {
                                Log.e("Firestore Error", error.message.toString())

                                return
                            }
                            for (dc: DocumentChange in value?.documentChanges!!) {
                                if (dc.type == DocumentChange.Type.ADDED) {
                                    courseArraylist.add(dc.document.toObject(Course::class.java))
                                    Log.d(
                                        "Function Recherche",
                                        courseArraylist[courseArraylist.size - 1].toString()
                                    )

                                }
                            }
                        }
                    })
            }

            if (!distance.isNullOrEmpty()) {
                //  distanceBetween();
                //getLastKno
                // FusedLocationProviderClient - Main class for receiving location updates.
                lateinit var fusedLocationProviderClient: FusedLocationProviderClient

                // LocationRequest - Requirements for the location updates, i.e.,
                // how often you should receive updates, the priority, etc.
                lateinit var locationRequest: LocationRequest

                // LocationCallback - Called when FusedLocationProviderClient
                // has a new Location
                lateinit var locationCallback: LocationCallback

                // This will store current location info
                var currentLocation: Location? = null

                fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)



            }
            return null;
        }

    }

}