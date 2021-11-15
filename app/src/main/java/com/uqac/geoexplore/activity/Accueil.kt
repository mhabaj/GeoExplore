package com.uqac.geoexplore.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase
import com.uqac.geoexplore.R

class Accueil : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_accueil)


    }

    fun RechercherCourse(view: View){
        val intent = Intent(this, Recherche::class.java)
        startActivity(intent)
    }
    fun Settings(view: View?) {
        val intent = Intent(this, Settings::class.java)
        startActivity(intent)

    }

    fun displayMap(view: android.view.View) {
        startActivity(Intent(this, DisplayCoursesMap::class.java))
    }


}