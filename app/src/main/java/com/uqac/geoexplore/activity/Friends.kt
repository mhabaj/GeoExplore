package com.uqac.geoexplore.activity


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import android.widget.AdapterView.OnItemClickListener
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.ktx.auth
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
        val dbUser = Firebase.auth.currentUser
        list = emptyList()
        // intent
        val intent = intent
        val message = intent!!.getStringExtra("Id")
        MainScope().launch {
            user = Functions.getUserFromUid(dbUser!!.uid)
            if (message != null) user!!.friends = user!!.friends!!.plus(message)


            db.collection("User").document(dbUser.uid.toString()).set(user!!)


            Log.d("App", user.toString())
            user = Functions.getUserFromUid(dbUser!!.uid)

            for(i in user?.friends!!) {
                list = list?.plus(Functions.getUserFromUid(i!!)?.shownName!!)
            }

            val adapter: ArrayAdapter<String> = ArrayAdapter<String>(this@Friends ,
                android.R.layout.simple_list_item_1, list!!
            )

            gridView!!.setAdapter(adapter)

            gridView!!.setOnItemClickListener(OnItemClickListener { parent, v, position, id ->
                Toast.makeText(
                    applicationContext,
                    (v as TextView).text, Toast.LENGTH_SHORT
                ).show()
                val intent = Intent(this@Friends, ProfileFriends::class.java)
                Log.d("App",user?.friends!!.get(position))
                intent.putExtra("id",user?.friends!!.get(position))
                startActivity(intent)
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



