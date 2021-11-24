package com.uqac.geoexplore.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.squareup.picasso.Picasso
import com.uqac.geoexplore.Functions
import com.uqac.geoexplore.R
import com.uqac.geoexplore.model.User
import kotlinx.android.synthetic.main.activity_profile.*
import kotlinx.android.synthetic.main.profil_friends.*
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
            if (user!!.profileImageUrl != null) {

                    var myUri = Uri.parse(user!!.profileImageUrl!!)

                    Picasso.get().load(myUri).into(imageView)
            }

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