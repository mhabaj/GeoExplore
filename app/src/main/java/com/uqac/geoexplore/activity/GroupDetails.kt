package com.uqac.geoexplore.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.uqac.geoexplore.Functions
import com.uqac.geoexplore.R
import com.uqac.geoexplore.model.User
import kotlinx.android.synthetic.main.activity_group_details.*
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class GroupDetails : AppCompatActivity() {
    private lateinit var courseName: String
    private lateinit var membersID: ArrayList<String>

    private lateinit var members: ArrayList<User>
    private lateinit var membersName: ArrayList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_group_details)

        courseName = intent.extras!!["courseName"] as String
        membersID = intent.extras!!["members"] as ArrayList<String>

        members = ArrayList()
        membersName = ArrayList()

        memberList.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, membersName)

        MainScope().launch {
            for (memberID in membersID) {
                membersName.add(Functions.getUserFromUid(memberID)!!.shownName!!)
                (memberList.adapter as ArrayAdapter<*>).notifyDataSetChanged()
            }
        }
    }
}