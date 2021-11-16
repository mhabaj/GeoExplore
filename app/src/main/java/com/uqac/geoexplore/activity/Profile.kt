package com.uqac.geoexplore.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.uqac.geoexplore.R
import com.uqac.geoexplore.model.User


class Profile : AppCompatActivity() {
    private lateinit var Name : TextView
    var f_auth: FirebaseAuth? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        Name = findViewById(R.id.name)
        val db = Firebase.firestore
        val user = FirebaseAuth.getInstance().currentUser
        Name!!.text = user?.displayName






    }




        fun Settings(view: View?) {
            val intent = Intent(this, Settings::class.java)
            startActivity(intent)

        }

        fun Friend(view: View) {
            val intent = Intent(this, Friends::class.java)
            startActivity(intent)

        }
    }

