package com.uqac.geoexplore.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration
import com.squareup.picasso.Picasso
import com.uqac.geoexplore.FirestoreUtil
import com.uqac.geoexplore.Functions
import com.uqac.geoexplore.R
import com.uqac.geoexplore.model.ChatMessage
import com.uqac.geoexplore.model.User
import kotlinx.android.synthetic.main.activity_chat_log.*
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import java.util.*
import kotlin.collections.ArrayList

class ChatLogActivity : AppCompatActivity() {
    val auth = FirebaseAuth.getInstance()
    val user = auth.currentUser
    val firestore = FirebaseFirestore.getInstance()
    private  lateinit  var currentUser: User

    val chatMessages = ArrayList<ChatMessage>()
    var chatRegistration: ListenerRegistration? = null
    var roomId: String? = null
    var other_user:String? =null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_log)
        checkUser()

        initList()
        setViewListeners()
    }

    private fun setViewListeners() {
        send_button.setOnClickListener {
            sendChatMessage()
        }
    }

    private fun initList() {
        recyclerview_chat_log.layoutManager = LinearLayoutManager(this)
        var adapter = ChatAdapter(chatMessages,User())
        recyclerview_chat_log.adapter = adapter
       FirestoreUtil.getUser{
            currentUser = it
            adapter = ChatAdapter(chatMessages, currentUser)
            recyclerview_chat_log.adapter = adapter

            listenForChatMessages()
        }

    }

    private fun listenForChatMessages() {
        roomId = intent.getStringExtra("INTENT_EXTRA_ROOMID")
        other_user = intent.getStringExtra("OTHER_USER")
        if (roomId == null) {
            finish()
            return
        }

        chatRegistration = firestore.collection("rooms")
            .document(roomId!!)
            .collection("messages")
            .addSnapshotListener { messageSnapshot, exception ->

                if (messageSnapshot == null || messageSnapshot.isEmpty)
                    return@addSnapshotListener

                chatMessages.clear()

                for (messageDocument in messageSnapshot.documents) {
                    chatMessages.add(
                        ChatMessage(
                            messageDocument["text"] as String,
                            messageDocument["user"] as String,
                            messageDocument["other_user"] as String,
                            messageDocument["timestamp"] as Timestamp
                        )
                    )
                }

                chatMessages.sortBy { it.timestamp }
                recyclerview_chat_log.adapter?.notifyDataSetChanged()
            }
    }

    private fun sendChatMessage() {
        val message = edittext_chat_log.text.toString()
        edittext_chat_log.setText("")
        Log.d("chat",chatMessages.toString())
        if(other_user == null){
            firestore.collection("rooms").document(roomId!!).collection("messages")
                .add(
                    mapOf(
                        Pair("text", message),
                        Pair("user",user!!.uid),
                        Pair("other_user",  chatMessages.last().user),
                        Pair("timestamp", Timestamp.now())
                    )
                )
        } else {
            firestore.collection("rooms").document(roomId!!).collection("messages")
                .add(
                    mapOf(
                        Pair("text", message),
                        Pair("user", user?.uid),
                        Pair("other_user", other_user),
                        Pair("timestamp", Timestamp.now())
                    )
                )
        }
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