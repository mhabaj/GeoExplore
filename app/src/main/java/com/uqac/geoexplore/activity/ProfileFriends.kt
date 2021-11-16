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
import com.uqac.geoexplore.Functions
import com.uqac.geoexplore.R
import com.uqac.geoexplore.model.User
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch


class ProfileFriends : AppCompatActivity() {
    private lateinit var Name : TextView
    var f_auth: FirebaseAuth? = null
    var user: User? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.profil_friends)

        Name = findViewById(R.id.name2)
        val intent = intent
        val message = intent!!.getStringExtra("id")
        MainScope().launch {
            user = Functions.getUserFromUid(message!!)
            Name!!.text = user?.shownName
        }


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