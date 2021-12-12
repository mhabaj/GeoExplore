package com.uqac.geoexplore.activity


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.uqac.geoexplore.ItemClickSupport
import com.uqac.geoexplore.R
import com.uqac.geoexplore.model.DetailMessage
import com.uqac.geoexplore.model.User
import kotlinx.android.synthetic.main.latest_message_row.*
import kotlinx.android.synthetic.main.latest_message_row.view.*


class MessageAdapter(val chatMessages: List<DetailMessage>, val user: User): RecyclerView.Adapter<MessageAdapter.ViewHolder>() {

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
            holder.itemView.message_textview_latest_message.text = chatMessage.text
            //val targetImageView = holder.itemView.imageview_latest_message
            //Picasso.get().load(uri).into(targetImageView)
            holder.itemView.username_textview_latest_message.text = chatMessage.id
        } else {
            holder.itemView.message_textview_latest_message.text = chatMessage.text
            val targetImageView = holder.itemView.imageview_latest_message
            Picasso.get().load(uri).into(targetImageView)
            holder.itemView.username_textview_latest_message.text = chatMessage.id

        }
    }

     fun getIdRoom(holder: ViewHolder): String{
        return chatMessages[holder.getAdapterPosition()].id;
    }



    class ViewHolder(inflater: LayoutInflater, parent: ViewGroup): RecyclerView.ViewHolder(
        inflater.inflate(R.layout.latest_message_row, parent, false)
    ) {

        private var username: TextView? = null
        private var chatText: TextView? = null
        private var button_enter: Button? =null

        init {
            username = itemView.findViewById(R.id.username_textview_latest_message)
            chatText = itemView.findViewById(R.id.message_textview_latest_message)
            button_enter = itemView.findViewById(R.id.button_enter)


        }


    }



}