package com.uqac.geoexplore.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.uqac.geoexplore.R

class Profile : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
    }


    fun Settings(view: View?) {
        val intent = Intent(this, Settings::class.java)
        startActivity(intent)

    }
}