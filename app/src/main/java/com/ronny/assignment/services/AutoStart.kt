package com.ronny.assignment.services

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class AutoStart :BroadcastReceiver() {
    override fun onReceive(p0: Context?, p1: Intent?) {
//        Toast.makeText(p0, "Hello there.........", Toast.LENGTH_LONG).show()
        val intent=Intent(p0,MyIntentServiceOnBoot::class.java)
        p0?.startService(intent)
    }
}