package com.example.geoexplorer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



    }


    fun SignIn(view: View?) {
        val intent = Intent(this, Enregistrer::class.java)
        startActivity(intent)

    }

    fun LogIn(view: View?) {


    }
}