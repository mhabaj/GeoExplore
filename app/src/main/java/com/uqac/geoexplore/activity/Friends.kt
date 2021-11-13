package com.uqac.geoexplore.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.*
import com.google.firebase.auth.FirebaseAuth
import com.uqac.geoexplore.R

class Friends : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.friends)
    }
    fun GenerateQrCode(view: View?) {
        val intent = Intent(this, QrCodeActivity::class.java)
        startActivity(intent)

    }

    fun ScanQrCode(view: View?) {
        val intent = Intent(this, ReadQrCode::class.java)
        startActivity(intent)

    }
}