package com.uqac.geoexplore.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import android.widget.AdapterView.OnItemClickListener
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.uqac.geoexplore.Functions
import com.uqac.geoexplore.R
import com.uqac.geoexplore.model.User
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch


class Friends : AppCompatActivity() {

    private var gridView: GridView? = null
    private var button: Button? = null
    var list:List<String>? = null
    var user: User? = null
    override  fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.friends)

        gridView = findViewById(R.id.gridView)
        val db = Firebase.firestore
        // intent
        val intent = intent
        val message = intent!!.getStringExtra("Id")
        MainScope().launch {
            user = Functions.getUserFromUid(FirebaseAuth.getInstance().currentUser!!.uid)
            Log.d("App",FirebaseAuth.getInstance().currentUser!!.uid)
            Log.d("App",user.toString()!!)
        }


        if(message != null) {
            user!!.friends += message
        }

        if(user != null) {
             list = user!!.friends
        }
        if(list != null) {

            val adapter: ArrayAdapter<String> = ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1, list!!
            )

            gridView!!.setAdapter(adapter)

            gridView!!.setOnItemClickListener(OnItemClickListener { parent, v, position, id ->
                Toast.makeText(
                    applicationContext,
                    (v as TextView).text, Toast.LENGTH_SHORT
                ).show()
            })
        }

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

