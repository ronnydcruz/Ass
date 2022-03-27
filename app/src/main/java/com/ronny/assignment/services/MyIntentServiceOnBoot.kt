package com.ronny.assignment.services

import android.app.IntentService
import android.content.Intent
import android.content.Context
import android.util.Log
import com.ronny.assignment.data.database.Item
import com.ronny.assignment.data.viewmodels.RespostoryClass
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.logging.Handler
import javax.inject.Inject

@AndroidEntryPoint
class MyIntentServiceOnBoot : IntentService("MyIntentServiceOnBoot") {
    @Inject
    lateinit var apiService: RespostoryClass

    override fun onHandleIntent(intent: Intent?) {
        var items: List<Item> = listOf()
        CoroutineScope(Dispatchers.IO).launch {
            val res = apiService.getRepo(
                "search/repositories",
                "stars",
                "desc",
                1,
                "android",
                "20")
            if (res.isSuccessful) {
                items=res.body()!!.items
                for (item in items) {
                    item.page= 1
                }
                apiService.createTable(items)
            }
        }
    }
}