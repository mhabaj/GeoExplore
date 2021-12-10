package com.uqac.geoexplore.activity

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.squareup.picasso.Picasso
import com.uqac.geoexplore.FirestoreUtil
import com.uqac.geoexplore.R
import com.uqac.geoexplore.model.ChatMessage
import com.uqac.geoexplore.model.User
import kotlinx.android.synthetic.main.list_item_chat.view.*

class ChatAdapter(val chatMessages: List<ChatMessage>, val user: User): RecyclerView.Adapter<ChatAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(inflater, parent)
    }

    override fun getItemCount(): Int {
        return chatMessages.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val chatMessage = chatMessages[position]

        val uri = user!!.profileImageUrl
        if (chatMessage.user == user.id) {
            holder.itemView.textview_chat_sent.text = chatMessage.text
            val targetImageView = holder.itemView.imageview_chat_from_row
            Picasso.get().load(uri).into(targetImageView)
            holder.itemView.textview_chat_received.visibility = View.GONE
            holder.itemView.imageview_chat_to_row.visibility = View.GONE
        } else {
            holder.itemView.textview_chat_received.text = chatMessage.text
            val targetImageView = holder.itemView.imageview_chat_to_row
            Picasso.get().load(uri).into(targetImageView)
            holder.itemView.textview_chat_sent.visibility = View.GONE
            holder.itemView.imageview_chat_from_row.visibility = View.GONE
        }
    }


    class ViewHolder(inflater: LayoutInflater, parent: ViewGroup): RecyclerView.ViewHolder(
        inflater.inflate(R.layout.list_item_chat, parent, false)
    ) {

        private var chatTextSent: TextView? = null
        private var chatTextReceived: TextView? = null

        init {
            chatTextSent = itemView.findViewById(R.id.textview_chat_sent)
            chatTextReceived = itemView.findViewById(R.id.textview_chat_received)
        }

    }

}