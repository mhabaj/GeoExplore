package com.uqac.geoexplore.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.core.text.set
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.uqac.geoexplore.R
import com.uqac.geoexplore.model.CheckPoint
import kotlinx.android.synthetic.main.activity_checkpoint_creation.*

class CheckpointCreation : AppCompatActivity() {
    private var checkpointNumber = 0

    private lateinit var courseID: String
    private lateinit var checkpoints: ArrayList<CheckPoint>

    private lateinit var checkpointID: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkpoint_creation)

        courseID = intent.extras?.get("courseID") as String

        checkpoints = ArrayList()
        checkpointNumber = 1
        findViewById<TextView>(R.id.checkpointID).text = checkpointNumber.toString()
    }

    fun addCheckpoint(view: android.view.View) {
        if (checkpointHint != null && checkpointHint.text.isNotEmpty()) {
            checkpointID = courseID.plus(checkpointNumber)
            val newCheckPoint = CheckPoint(checkpointID, checkpointNumber, checkpointHint.text.toString())
            checkpoints.add(newCheckPoint)

            checkpointHint.text.clear()
            checkpointNumber++
            findViewById<TextView>(R.id.checkpointID).text = checkpointNumber.toString()
            findViewById<TextView>(R.id.checkpointNumber).text = checkpoints.size.toString()
        }
    }

    fun generateCheckpoints(view: android.view.View) {
        if (checkpoints.isNotEmpty()) {
            val db = Firebase.firestore
            db.collection("Course").document(courseID).update("checkPoint", checkpoints)
        }
    }
}