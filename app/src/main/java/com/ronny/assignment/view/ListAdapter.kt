package com.ronny.assignment.view

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.ronny.assignment.R
import com.ronny.assignment.data.database.Item

class ListAdapter(val mList: ArrayList<Item>,val context: AppCompatActivity) :RecyclerView.Adapter<ListAdapter.ViewHolder>() {
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val textView: TextView = itemView.findViewById(R.id.textView)
        val watches: TextView = itemView.findViewById(R.id.watches)
        val conn:ConstraintLayout = itemView.findViewById(R.id.conn)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_item, parent, false)

        return ViewHolder(view)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val ItemsViewModel = mList[position]

        // sets the text to the textview from our itemHolder class
        holder.textView.text = ItemsViewModel.name.toString()
        holder.watches.text = ItemsViewModel.watchers.toString()
        holder.conn.setOnClickListener {
            val str=Gson().toJson(mList[position])
            val intent=Intent(context,MainActivity2::class.java)
            intent.putExtra("listobject",str)
            context.startActivity(intent)
        }

    }

    fun addtolist(lisst:List<Item>)
    {
        mList.addAll(lisst)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
    return  mList.size
    }
}