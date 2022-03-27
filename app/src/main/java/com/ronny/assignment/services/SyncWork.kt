package com.ronny.assignment.services

import android.content.Context
import android.util.Log
import androidx.hilt.work.HiltWorker
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.ronny.assignment.data.api.ApiService
import com.ronny.assignment.data.database.Item
import com.ronny.assignment.data.viewmodels.RespostoryClass
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltWorker
class SyncWork @AssistedInject constructor(@Assisted context: Context,@Assisted workerParameters: WorkerParameters, val apiService: RespostoryClass):Worker(context,workerParameters) {
    override fun doWork(): Result {
        var items: List<Item> = listOf()
        Log.v("Sunc","Synced")
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
        return Result.success()
    }
}