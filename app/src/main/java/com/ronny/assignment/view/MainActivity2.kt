package com.ronny.assignment.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.google.gson.Gson
import com.ronny.assignment.R
import com.ronny.assignment.data.database.Item

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val t=intent.getStringExtra("listobject")
        val item=Gson().fromJson<Item>(t,Item::class.java)
        val txt=findViewById<TextView>(R.id.name)
        val watches=findViewById<TextView>(R.id.watches)
        val url=findViewById<TextView>(R.id.url)
        val orgoruser=findViewById<TextView>(R.id.orgoruser)

        txt.setText(item.full_name.toString())
        watches.setText(item.watchers.toString())
        url.setText(item.html_url.toString())
        orgoruser.setText(item.owner.type.toString())
    }
}