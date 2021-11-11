package com.uqac.geoexplore.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.uqac.geoexplore.R

class LogIn : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)
    }


    fun SignUp(view: View?) {
        val intent = Intent(this, Enregistrer::class.java)
        startActivity(intent)

    }

    fun PageAccueil(view: View?) {
        val intent = Intent(this, Accueil::class.java)
        startActivity(intent)

    }

}