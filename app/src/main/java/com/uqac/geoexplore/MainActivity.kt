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

