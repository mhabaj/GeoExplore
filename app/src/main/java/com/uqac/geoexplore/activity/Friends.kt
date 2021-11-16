package com.uqac.geoexplore.activity

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import android.widget.AdapterView.OnItemClickListener
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.uqac.geoexplore.Functions
import com.uqac.geoexplore.R
import com.uqac.geoexplore.model.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch



class Friends : AppCompatActivity() {

    private var gridView: GridView? = null
    private var button: Button? = null
    var list:List<String>? = null
    var listamis:List<String>? = null
    var user: User? = null
    override  fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.friends)

        gridView = findViewById(R.id.gridView)
        val db = Firebase.firestore
        val dbUser = Firebase.auth.currentUser

        // intent
        val intent = intent
        val message = intent!!.getStringExtra("Id")
        MainScope().launch {
            user = Functions.getUserFromUid(dbUser!!.uid)
            if (message != null) user!!.friends = user!!.friends!!.plusElement(message)
            //Functions.getUserFromUid(message)?.shownName!!
            val profileUpdates = userProfileChangeRequest {

            }
            dbUser!!.updateProfile(profileUpdates)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val user = User(
                            dbUser.uid,
                            dbUser.displayName,
                            dbUser.email.toString(),
                            user!!.friends
                        )
                        db.collection("User")
                            .document(Firebase.auth.currentUser?.uid.toString()).set(user)

                    }
                }
            Log.d("App", user.toString())
            user = Functions.getUserFromUid(dbUser!!.uid)

            if (user != null) list = user!!.friends!!
                Log.d("App", "ajout ami")
                val adapter: ArrayAdapter<String> = ArrayAdapter<String>(this@Friends ,
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



