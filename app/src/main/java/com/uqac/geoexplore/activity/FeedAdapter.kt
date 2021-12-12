package com.uqac.geoexplore.activity

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.uqac.geoexplore.R
import com.uqac.geoexplore.model.ChatMessage
import com.uqac.geoexplore.model.User
import kotlinx.android.synthetic.main.list_item_feed.view.*

class FeedAdapter(val chatMessages: List<ChatMessage>, val user: User): RecyclerView.Adapter<FeedAdapter.ViewHolder>() {

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
            holder.itemView.textview_feed_sent.text = chatMessage.text
            val targetImageView = holder.itemView.imageview_feed_from_row
            Picasso.get().load(uri).into(targetImageView)
            holder.itemView.textview_feed_received.visibility = View.GONE
            holder.itemView.imageview_feed_to_row.visibility = View.GONE
        } else {
            holder.itemView.textview_feed_received.text = chatMessage.text
            val targetImageView = holder.itemView.imageview_feed_to_row
            Picasso.get().load(uri).into(targetImageView)
            holder.itemView.textview_feed_sent.visibility = View.GONE
            holder.itemView.imageview_feed_from_row.visibility = View.GONE
        }
    }


    class ViewHolder(inflater: LayoutInflater, parent: ViewGroup): RecyclerView.ViewHolder(
        inflater.inflate(R.layout.list_item_feed, parent, false)
    ) {

        private var chatTextSent: TextView? = null
        private var chatTextReceived: TextView? = null

        init {
            chatTextSent = itemView.findViewById(R.id.textview_feed_sent)
            chatTextReceived = itemView.findViewById(R.id.textview_feed_received)
        }

    }

}