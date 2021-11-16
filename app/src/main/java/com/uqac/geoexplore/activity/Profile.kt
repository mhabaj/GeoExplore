package com.uqac.geoexplore.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.uqac.geoexplore.R

import android.widget.ViewSwitcher




class Profile : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)


        var m_name = findViewById<TextView>(R.id.textname)
        var m_city = findViewById<EditText>(R.id.city)
        var m_age = findViewById<EditText>(R.id.age)


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

    fun switcher(view: View) {
        var m_age = findViewById<EditText>(R.id.age)
        var btnValider = findViewById<Button>(R.id.buttonvalider)
        btnValider.visibility = View.VISIBLE
        m_age.isEnabled = true

    }

    fun switchCity(view: View) {
        var btnValider = findViewById<Button>(R.id.buttonvalider)
        btnValider.visibility = View.VISIBLE
        var m_city = findViewById<EditText>(R.id.city)
        m_city.isEnabled = true
    }

    fun valider(view: View){

        var m_age = findViewById<EditText>(R.id.age)
        var m_city = findViewById<EditText>(R.id.city)
        m_age.isEnabled = false
        m_city.isEnabled= false
        var btnValider = findViewById<Button>(R.id.buttonvalider)
        btnValider.visibility = View.INVISIBLE



    }
}