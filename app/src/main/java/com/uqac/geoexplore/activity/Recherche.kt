package com.uqac.geoexplore.activity

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.uqac.geoexplore.R
import com.uqac.geoexplore.model.Course
import kotlinx.android.synthetic.main.activity_recherche.*
import kotlinx.android.synthetic.main.create_course.*




class Recherche : AppCompatActivity() {
    private lateinit var db: FirebaseFirestore
    private lateinit var courseRecycleview : RecyclerView
    private lateinit var courseArraylist : ArrayList<Course>
    private lateinit var my_adapter :MyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recherche)

        courseRecycleview = findViewById(R.id.courseList)
        courseRecycleview.layoutManager= LinearLayoutManager(this)
        courseRecycleview.setHasFixedSize(true)

        db = FirebaseFirestore.getInstance()
        db.collection("Course")





    }



    private fun getCourseData(){

        db = Firebase.firestore
        db.collection("Course")




    }



}