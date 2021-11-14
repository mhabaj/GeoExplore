package com.uqac.geoexplore

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.uqac.geoexplore.activity.Enregistrer
import com.uqac.geoexplore.activity.LogIn
import com.uqac.geoexplore.model.Course
import com.uqac.geoexplore.model.CourseMiscDetails
import com.uqac.geoexplore.model.User


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val db = Firebase.firestore
        val userdb = Firebase.auth.currentUser

        // Create a new user with a first and last name
        var user = User(userdb?.uid.toString(),"premierTest", "mhabaj99@hotmail.com")


        // Add a new document with a generated ID
        db.collection("User")
            .add(user)
            .addOnSuccessListener { documentReference ->
                Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error adding document", e)
            }

        var CourseMiscDetailsTest = CourseMiscDetails(user, "22-09-1999","5",5,"Parcours 1 lets go trop bien ce parcours")
        var parcoursTest = Course("5141", "parcoursTest", CourseMiscDetailsTest,"7844784 784747")

        // Add a new document with a generated ID
        db.collection("Course")
            .add(CourseMiscDetailsTest)
            .addOnSuccessListener { documentReference ->
                Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error adding document", e)
            }


    }


    fun signUp(view: View?) {
        val intent = Intent(this, Enregistrer::class.java)
        startActivity(intent)

    }

    fun logIn(view: View?) {
        val intent = Intent(this, LogIn::class.java)
        startActivity(intent)

    }


}

/*
ILONA CODE QR:
 override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.friends)
    }
    fun GenerateQrCode(view: View?) {
        val intent = Intent(this, QrCodeActivity::class.java)
        startActivity(intent)

    }

    fun ScanQrCode(view: View?) {
        val intent = Intent(this, ReadQrCode::class.java)
        startActivity(intent)

    }

////////////////////////////////////////////////////////////
Code pour ajouter objets dans la BDD:

 val db = Firebase.firestore
        val userdb = Firebase.auth.currentUser

        // Create a new user with a first and last name
        var user = User(userdb?.uid.toString(),"premierTest", "mhabaj99@hotmail.com")


        // Add a new document with a generated ID
                db.collection("User")
                    .add(user)
                    .addOnSuccessListener { documentReference ->
                        Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
                    }
                    .addOnFailureListener { e ->
                        Log.w(TAG, "Error adding document", e)
                    }
////////////////////////////////////////////////////////

Code pour recuprer objets de la bdd:



 */