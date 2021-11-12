package com.uqac.geoexplore.activity

import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.uqac.geoexplore.R
import com.uqac.geoexplore.model.Course
import com.uqac.geoexplore.model.CourseMiscDetails
import com.uqac.geoexplore.model.User
import java.time.Instant
import java.util.*

class CourseCreation : AppCompatActivity() {
    private lateinit var courseName: EditText
    private lateinit var courseDescription: EditText
    private lateinit var courseLocation: EditText
    private lateinit var courseInterests: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.create_course)

        courseName = findViewById(R.id.courseName)
        courseDescription = findViewById(R.id.courseDescription)
        courseLocation = findViewById(R.id.courseLocation)
        courseInterests = findViewById(R.id.courseInterests)

        courseLocation.setText(intent.extras?.get("location").toString())
    }

    fun addCourseInDatabase(view: android.view.View) {
        var courseDetails = CourseMiscDetails()
        courseDetails.publicationDate = Date.from(Instant.now())

        val f_auth = FirebaseAuth.getInstance()
        if (f_auth.currentUser != null) {
            courseDetails.creator = User()
            //TODO: Get current User object
        }

        var newCourse = Course(courseName.text.toString(), courseLocation.text.toString(), courseDetails)
        println("Course crée : " + newCourse.name)
    }
}