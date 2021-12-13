package com.uqac.geoexplore.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.SearchView
import android.widget.Toast
import androidx.core.view.contains
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.uqac.geoexplore.Functions
import com.uqac.geoexplore.R
import com.uqac.geoexplore.model.Group
import com.uqac.geoexplore.model.User
import kotlinx.android.synthetic.main.activity_group_creation.*
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class GroupCreation : AppCompatActivity() {

    private lateinit var friendsList: ArrayList<String>
    private lateinit var courseID: String

    private lateinit var usersInGroup: ArrayList<String>
    private lateinit var groupToAdd: Group

    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_group_creation)

        courseID = intent.extras!!["courseID"] as String

        val dbUser = Firebase.auth.currentUser
        db = Firebase.firestore

        friendsList = ArrayList()
        usersInGroup = ArrayList()

        groupToAdd = Group(ArrayList())

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, friendsList)
        groupList.adapter = adapter

        MainScope().launch {
            val user = Functions.getUserFromUid(dbUser!!.uid)
            usersInGroup.add(user?.shownName!!)

            // Displaying only the user's friends

            /*for (i in user.friends!!) {
                friendsList.add(Functions.getUserFromUid(i)?.shownName!!)
            }*/

            // Displaying all users of the app

            db.collection("User").get().addOnSuccessListener {
                users ->
                for (userToAdd in users){
                    friendsList.add(userToAdd.toObject<User>().shownName!!)
                    println("Adding user to list: " + userToAdd.toObject<User>().shownName!!)
                }

                updateGroupMembersText()
                (groupList.adapter as ArrayAdapter<*>).notifyDataSetChanged()
            }
        }

        searchFriend.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }
            override fun onQueryTextChange(p0: String?): Boolean {
                //Start filtering the list as user start entering the characters
                adapter.filter.filter(p0)
                return false
            }
        })

        groupList.setOnItemClickListener { parent, _, position, _ ->
            val selectedUserName = parent.getItemAtPosition(position) as String

            if (usersInGroup.contains(selectedUserName)) {
                usersInGroup.remove(selectedUserName)
            }
            else {
                usersInGroup.add(selectedUserName)
            }

            updateGroupMembersText()
            (groupList.adapter as ArrayAdapter<*>).notifyDataSetChanged()
        }
    }

    private fun updateGroupMembersText() {
        groupMembers.text = "Group members : "
        var first = true
        for (user in usersInGroup) {
            if (first)
                groupMembers.text = groupMembers.text as String + user
            else
                groupMembers.text = groupMembers.text as String + ", " + user
            first = false
        }
    }

    fun createGroupInDatabase(view: android.view.View) {
        var courseName: String? = null
        MainScope().launch {
            groupToAdd = Functions.retrieveUid(usersInGroup)!!
            for (userID in groupToAdd.participants!!)
                println(userID)

            db.collection("Course").document(courseID).update("groups", FieldValue.arrayUnion(groupToAdd)).addOnSuccessListener {
                println("Group successfully added !")
            }
            courseName = Functions.getCourseFromId(courseID)?.name!!
        }
        println("Adding to course $courseID a new group")
        Toast.makeText(this, "Group created !", Toast.LENGTH_LONG).show()
        startActivity(
            Intent(this, CourseDetails::class.java)
            .putExtra("courseName", courseName))
    }
}