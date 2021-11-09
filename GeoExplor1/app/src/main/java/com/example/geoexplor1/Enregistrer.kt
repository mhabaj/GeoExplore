package com.example.geoexplor1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import com.google.firebase.database.FirebaseDatabase
import java.util.jar.Attributes


class Enregistrer : AppCompatActivity() {
    var database = FirebaseDatabase.getInstance()
    var name = database.getReference("message")
    var email = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_enregistrer)
        var m_name = findViewById<EditText>(R.id.Name);
    }

    fun Continue(view: View?) {
        // myRef.setValue();





    }


    fun Retour(view: View?) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)

    }
}