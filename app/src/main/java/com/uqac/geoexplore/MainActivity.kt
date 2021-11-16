package com.uqac.geoexplore

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.view.View

import com.uqac.geoexplore.activity.Enregistrer
import com.uqac.geoexplore.activity.LogIn
import com.uqac.geoexplore.model.User
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch


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
