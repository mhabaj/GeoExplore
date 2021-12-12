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
import com.uqac.geoexplore.FirestoreUtil
import com.uqac.geoexplore.R
import com.uqac.geoexplore.model.ChatMessage
import com.uqac.geoexplore.model.User
import kotlinx.android.synthetic.main.activity_feed.*
import java.util.*
import kotlin.collections.ArrayList

class FeedActivity : AppCompatActivity() {
    val auth = FirebaseAuth.getInstance()
    val user = auth.currentUser
    val firestore = FirebaseFirestore.getInstance()
    private  lateinit  var currentUser: User

    val chatMessages = ArrayList<ChatMessage>()
    var chatRegistration: ListenerRegistration? = null
    var roomId: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feed)
        checkUser()

        initList()
        setViewListeners()
    }

    private fun setViewListeners() {
        send_button_feed.setOnClickListener {
            sendChatMessage()
        }
    }

    private fun initList() {

        recyclerview_feed.layoutManager = LinearLayoutManager(this)
        var adapter = FeedAdapter(chatMessages,User())
        recyclerview_feed.adapter = adapter
        FirestoreUtil.getUser{
            currentUser = it
            adapter = FeedAdapter(chatMessages, currentUser)
            recyclerview_feed.adapter = adapter
            Log.d("adapter", recyclerview_feed.adapter.toString())
            listenForChatMessages()
        }

    }

    private fun listenForChatMessages() {
        roomId = intent.getStringExtra("INTENT_EXTRA_ROOMID")
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
                            messageDocument["timestamp"] as Timestamp
                        )
                    )
                }

                chatMessages.sortBy { it.timestamp }
                recyclerview_feed.adapter?.notifyDataSetChanged()
            }
    }

    private fun sendChatMessage() {
        val message = edittext_feed.text.toString()
        edittext_feed.setText("")

        firestore.collection("rooms").document(roomId!!).collection("messages")
            .add(
                mapOf(
                    Pair("text", message),
                    Pair("user",  user?.uid),
                    Pair("timestamp", Timestamp.now())
                )
            )
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