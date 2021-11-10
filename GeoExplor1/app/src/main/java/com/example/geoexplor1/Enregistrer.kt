package com.example.geoexplor1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import java.util.jar.Attributes
import androidx.annotation.NonNull

import com.google.android.gms.tasks.OnCompleteListener





class Enregistrer : AppCompatActivity() {

    private  var m_name :(EditText) = TODO()
    private var m_email:(EditText) = TODO()
    private var m_password:(EditText) = TODO()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_enregistrer)
/*
        m_name = findViewById<EditText>(R.id.editTextTextPersonName)
        m_email = findViewById<EditText>(R.id.editTextTextEmailAddress)
        m_password = findViewById<EditText>(R.id.editTextTextPassword)
*/



    }/*

    fun Continue(view: View?) {
        var f_auth = FirebaseAuth.getInstance()

        //Verification
        var email = m_email.text.toString().trim()
        var password = m_password.text.toString().trim()

        if(TextUtils.isEmpty(email)){
            m_email.setError("Email is Required.")
            return
        }
        if(TextUtils.isEmpty(password)){
            m_password.setError("Password is Required.")
            return
        }

        f_auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener { taskId ->
            if(taskId.isSuccessful) {
                Toast.makeText(this@Enregistrer, "User Created.", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
            else Toast.makeText(this@Enregistrer, "Error !!"+ taskId.exception , Toast.LENGTH_SHORT).show()

        }

    }*/



    fun LogIn(view: View?) {
        val intent = Intent(this, LogIn::class.java)
        startActivity(intent)

    }




}


