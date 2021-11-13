package com.uqac.geoexplore.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
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

    fun Profil(view: View) {
        val intent = Intent(this, Profile::class.java)
        startActivity(intent)

    }




}