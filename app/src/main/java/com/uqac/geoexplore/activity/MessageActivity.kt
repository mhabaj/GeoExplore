package com.uqac.geoexplore.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration
import com.uqac.geoexplore.FirestoreUtil
import com.uqac.geoexplore.R
import com.uqac.geoexplore.model.DetailMessage
import com.uqac.geoexplore.model.User
import kotlinx.android.synthetic.main.activity_chat_log.*
import kotlinx.android.synthetic.main.activity_feed.*
import kotlinx.android.synthetic.main.activity_new_message.*
import kotlinx.android.synthetic.main.latest_message_row.*
import kotlinx.android.synthetic.main.latest_message_row.view.*

class MessageActivity : AppCompatActivity() {
    val auth = FirebaseAuth.getInstance()
    val user = auth.currentUser
    val firestore = FirebaseFirestore.getInstance()
    private  lateinit  var currentUser: User

    val usernameMessages = ArrayList<String>()
    val chatMessages = ArrayList<DetailMessage>()
    val LastMessages = ArrayList<DetailMessage>()
    var chatRegistration: ListenerRegistration? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_message)
        checkUser()

        initList()

    }

    private fun initList() {
        if (user == null )
            return

        recyclerview_newmessage.layoutManager = LinearLayoutManager(this)
        var adapter = MessageAdapter(LastMessages,User())
        recyclerview_newmessage.adapter = adapter
        FirestoreUtil.getUser{
            currentUser = it
            adapter = MessageAdapter(LastMessages, currentUser)
            recyclerview_newmessage.adapter = adapter
            firestore.collection("User")
                .document(currentUser.id.toString())
                .collection("rooms")
                .addSnapshotListener {usernameSnapshot,exception ->
                    if (usernameSnapshot == null || usernameSnapshot.isEmpty)
                        return@addSnapshotListener
                    usernameMessages.clear()
                    for(usernameDocument in usernameSnapshot.documents){
                        usernameMessages.add(usernameDocument.get("id") as String)
                        Log.d("test",firestore.collection("User")
                                .document(currentUser.id.toString())
                                .collection("rooms").document(usernameDocument.toString()).collection(usernameDocument.toString()).id)
                    }

                    Log.d("liste username messages",usernameMessages.toString())
                    for(username in usernameMessages) {
                        listenForChatMessages(username)
                    }
                }


        }

    }

    private fun listenForChatMessages(username: String) {
        chatRegistration = firestore.collection("rooms").document(username)
                 .collection("messages")
                 .addSnapshotListener { messageSnapshot, exception ->
                     if (messageSnapshot == null || messageSnapshot.isEmpty) {
                         return@addSnapshotListener
                     }
                     chatMessages.clear()
                     LastMessages.clear()
                     for (messageDocument in messageSnapshot.documents) {
                         if(messageDocument["other_user"]!=null){
                        chatMessages.add(
                            DetailMessage(
                                messageDocument["text"] as String,
                                messageDocument["user"] as String,
                                messageDocument["other_user"] as String,
                                messageDocument["timestamp"] as Timestamp,
                                username as String
                            )
                         )} else {

                         }
                     }
                     chatMessages.sortBy { it.timestamp }
                     if(chatMessages.isEmpty()){}
                     else {
                         LastMessages.add(chatMessages.last())
                         LastMessages.sortBy { it.user }
                         recyclerview_newmessage.adapter?.notifyDataSetChanged()
                     }
                 }
    }

    fun enterRoom(view: View) {
        val holder= recyclerview_newmessage!!.findContainingViewHolder(view) as MessageAdapter.ViewHolder
        val adapter= recyclerview_newmessage.adapter as MessageAdapter
        val roomId = adapter.getIdRoom(holder)
        if (roomId.isEmpty()) {
            return
        }
        val intent = Intent(this ,ChatLogActivity::class.java)
        intent.putExtra("INTENT_EXTRA_ROOMID", roomId)
        startActivity(intent)
    }

    private fun checkUser() {
        if (user == null)
            launchLogin()
    }

    private fun launchLogin() {
        val intent = Intent(this, LogIn::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish()
    }

    private fun signOut() {
        auth.signOut()
        launchLogin()
    }

    override fun onDestroy() {
        chatRegistration?.remove()
        super.onDestroy()
    }
}