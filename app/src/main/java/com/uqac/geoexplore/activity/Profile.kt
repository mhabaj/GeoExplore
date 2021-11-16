package com.uqac.geoexplore.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.uqac.geoexplore.R

class Profile : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)


        var m_name = findViewById<TextView>(R.id.textname)
        val user = FirebaseAuth.getInstance().currentUser
        m_name!!.text = user?.displayName

    }

    fun settings(view: View?) {
        val intent = Intent(this, Settings::class.java)
        startActivity(intent)

    }

    fun friend(view: View) {
        val intent = Intent(this, Friends::class.java)
        startActivity(intent)

    }
}