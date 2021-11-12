package com.uqac.geoexplore

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.uqac.geoexplore.activity.Enregistrer
import com.uqac.geoexplore.activity.LogIn


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }


    fun signUp(view: View?) {
        val intent = Intent(this, Enregistrer::class.java)
        startActivity(intent)

    }

    fun logIn(view: View?) {
        val intent = Intent(this, LogIn::class.java)
        startActivity(intent)

    }


}

/*
ILONA CODE QR:
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


 */