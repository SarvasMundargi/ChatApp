package com.example.chatapplication

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth

class UserAdapter(private val context:Context, private val userlist: ArrayList<User>): RecyclerView.Adapter<UserAdapter.userViewHolder>() {

    class userViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView=itemView.findViewById(R.id.tv_username)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): userViewHolder {
        val view=LayoutInflater.from(context).inflate(R.layout.user_layout,parent,false)
        return userViewHolder(view)
    }

    override fun getItemCount(): Int {
        return userlist.size
    }

    override fun onBindViewHolder(holder: userViewHolder, position: Int) {
        val currentuser=userlist[position]
        holder.name.text=currentuser.name

        holder.itemView.setOnClickListener {
            val intent= Intent(context,ChatActivity::class.java)
            intent.putExtra("name",currentuser.name)
            intent.putExtra("uid",currentuser.uid)
            context.startActivity(intent)
        }
    }
}