package com.uqac.geoexplore.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.uqac.geoexplore.R

class LogIn : AppCompatActivity() {
    private var m_email: EditText? = null
    private var m_password: EditText? = null
    private val TAG = "MyActivity"
    private var progress_bar: ProgressBar? = null

    private var m_Resultat: TextView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)


        m_email = findViewById<EditText>(R.id.editTextTextEmailAddress)
        m_password = findViewById<EditText>(R.id.editTextTextPassword)
        progress_bar = findViewById<ProgressBar>(R.id.progressBar)
        m_Resultat = findViewById<TextView>(R.id.textAffichageResultat)

    }


    fun SignUp(view: View?) {
        val intent = Intent(this, Enregistrer::class.java)
        startActivity(intent)

    }

    fun PageAccueil(view: View?) {


        var f_auth = FirebaseAuth.getInstance()
/*
        if(f_auth.currentUser != null) {
            startActivity(Intent(applicationContext, MainActivity::class.java))
            finish()
        }*/


        //Verification
        var email = m_email?.text.toString().trim()
        var password = m_password?.text.toString().trim()

        if(TextUtils.isEmpty(email)){
            m_email?.setError("Email is Required.")
            return
        }

        if(TextUtils.isEmpty(password)){
            m_password?.setError("Password is Required.")
            return
        }
        if(m_password?.length()!! <6 ){
            m_password?.setError("Password must have at least 6 characters.")
            return
        }

        progress_bar?.setVisibility(View.VISIBLE)
        m_Resultat?.setVisibility(View.VISIBLE)

        // Enregistrer l'utilisateur dans la base de données

        f_auth.signInWithEmailAndPassword(email,password).addOnCompleteListener { taskId ->
            if(taskId.isSuccessful) {
                m_Resultat?.setText("Logged in Succesfully ! ")
                startActivity(Intent(applicationContext, Accueil::class.java))
            }
            else {
                m_Resultat?.setText("Error !!"+ taskId.exception)
                progress_bar?.setVisibility(View.INVISIBLE)
            }
        }


    }


}