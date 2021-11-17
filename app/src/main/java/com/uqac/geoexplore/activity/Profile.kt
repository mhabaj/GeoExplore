package com.uqac.geoexplore.activity

import android.app.Activity
import android.content.ContentValues.TAG
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.uqac.geoexplore.Functions
import com.uqac.geoexplore.GlideApp
import com.uqac.geoexplore.R
import com.uqac.geoexplore.model.User
import kotlinx.android.synthetic.main.activity_profile.*
import kotlinx.android.synthetic.main.profil_friends.*
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import java.util.*

class Profile : AppCompatActivity() {
    var user: User? = null
    var dbUser: FirebaseUser? = null
    var image:String? = null
    var db: FirebaseFirestore? =null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        var m_name = findViewById<TextView>(R.id.textname)

        db = Firebase.firestore
       dbUser = Firebase.auth.currentUser

        m_name!!.text = dbUser?.displayName

        selectphoto_button_register.setOnClickListener {
            Log.d(TAG, "Try to show photo selector")

            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, 0)
        }

        MainScope().launch {
            user = Functions.getUserFromUid(dbUser!!.uid)
            Log.d("App", user.toString())
            if (user!!.profileImageUrl != null) {
                var storageReference = FirebaseStorage.getInstance().getReference().child("myimage");

                var myUri = Uri.parse(user!!.profileImageUrl!!)
                //val ref = FirebaseStorage.getInstance().getReference("/images/").child(myUri.path!!).downloadUrl.
                GlideApp.with(this@Profile).load(myUri).into(selectphoto_imageview_register)

                //Picasso.get().load(ref).into(selectphoto_imageview_register)
                selectphoto_button_register.alpha = 0f
            }
        }

    }

    var selectedPhotoUri: Uri? = null

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 0 && resultCode == Activity.RESULT_OK && data != null) {
            // proceed and check what the selected image was....
            Log.d(TAG, "Photo was selected")

            selectedPhotoUri = data.data

            val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, selectedPhotoUri)

            selectphoto_imageview_register.setImageBitmap(bitmap)

            selectphoto_button_register.alpha = 0f

            val filename = UUID.randomUUID().toString()
            val ref = FirebaseStorage.getInstance().getReference("/images/$filename")

            val uploadTask = ref.putFile(selectedPhotoUri!!)
            uploadTask.continueWith {
                if (!it.isSuccessful) {
                    it.exception?.let { t ->
                        throw t
                    }
                }
                ref.downloadUrl
            }.addOnCompleteListener {
                if (it.isSuccessful) {
                    it.result!!.addOnSuccessListener { task ->
                        image = task.toString()
                        MainScope().launch {
                            user = Functions.getUserFromUid(dbUser!!.uid)
                            user!!.profileImageUrl = image
                            Log.d("App", image!!.toString())
                            db!!.collection("User").document(dbUser!!.uid.toString()).set(user!!)

                            Log.d("App", user.toString())
                        }
                    }
                }
            }


        }
    }

    fun settings(view: View?) {
        val intent = Intent(this, Settings::class.java)
        startActivity(intent)

    }

    fun friend(view: View) {
        val intent = Intent(this, Friends::class.java)
        startActivity(intent)

    }
}