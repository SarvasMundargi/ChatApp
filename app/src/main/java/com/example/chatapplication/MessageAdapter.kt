package com.example.chatapplication

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.google.firebase.auth.FirebaseAuth

class MessageAdapter(val context: Context,val messagelist: ArrayList<Message>): RecyclerView.Adapter<ViewHolder>() {

    val ITEM_RECEIVE=1
    val ITEM_SENT=2
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        if(viewType==1){
            val view= LayoutInflater.from(context).inflate(R.layout.receive,parent,false)
            return ReceiveViewHolder(view)
        }
        else{
            val view= LayoutInflater.from(context).inflate(R.layout.sent,parent,false)
            return SentViewHolder(view)
        }
    }

    override fun getItemCount(): Int {
        return messagelist.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentmessage=messagelist[position]

        if(holder.javaClass==SentViewHolder::class.java){
            val viewHolder=holder as SentViewHolder


            holder.sentmessage.text=currentmessage.message
        }
        else{
            val viewHolder=holder as ReceiveViewHolder
            holder.receivemessage.text=currentmessage.message
        }

    }

    override fun getItemViewType(position: Int): Int {
        val currentmessage=messagelist[position]
        if(FirebaseAuth.getInstance().currentUser!!.uid.equals(currentmessage.senderid)){
            return ITEM_SENT
        }
        else return ITEM_RECEIVE
    }

    class SentViewHolder(itemView: View) : ViewHolder(itemView) {
        val sentmessage: TextView=itemView.findViewById(R.id.text_sent_message)
    }

    class ReceiveViewHolder(itemView: View) : ViewHolder(itemView) {
        val receivemessage: TextView=itemView.findViewById(R.id.text_receive_message)
    }
}