package com.uqac.geoexplore.activity

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.google.android.gms.tasks.Task
import com.google.android.gms.tasks.Tasks.await
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.google.type.DateTime
import com.uqac.geoexplore.R
import com.uqac.geoexplore.model.Course
import java.time.Instant
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

class CourseDetails : AppCompatActivity() {
    private lateinit var course: Course

    private lateinit var courseName: TextView
    private lateinit var courseLocation: TextView
    private lateinit var courseDescription: TextView
    private lateinit var courseInterests: TextView
    private lateinit var courseDate: TextView
    private lateinit var courseTime: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_course)

        val selectedCourseName = intent.extras!!["courseName"] as String
        getCourseFromName(selectedCourseName)
    }

    private fun fillInformation() {
        courseName = findViewById(R.id.courseNameView)
        courseName.text = course.name

        courseLocation = findViewById(R.id.courseLocationView)
        courseLocation.text = course.location.latitude.toString() + ", " + course.location.longitude

        courseDescription = findViewById(R.id.descriptionView)
        courseDescription.text = course.miscInfo.description

        courseInterests = findViewById(R.id.courseInterestView)

        courseDate = findViewById(R.id.editTextDate)
        courseDate.hint = LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE)

    }

    fun getCourseFromName(name: String){
        val db = Firebase.firestore
        db.collection("Course")
            .whereEqualTo("name", name)
            .get()
            .addOnSuccessListener { result ->
                course = result.first().toObject()
                fillInformation()
            }
            .addOnFailureListener { exception ->
                Log.d(ContentValues.TAG, "Error getting documents: ", exception)
            }
    }
}