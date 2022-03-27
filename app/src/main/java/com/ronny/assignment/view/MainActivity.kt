package com.ronny.assignment.view

import android.content.Context
import android.net.ConnectivityManager
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.ronny.assignment.R
import com.ronny.assignment.data.api.Status
import com.ronny.assignment.data.database.Item
import com.ronny.assignment.data.viewmodels.RespostoryClass
import com.ronny.assignment.data.viewmodels.ViewmodelClass
import com.ronny.assignment.services.SyncWork
import dagger.hilt.android.AndroidEntryPoint
import java.time.Duration
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var apiService: RespostoryClass


    val viewmodelClass: ViewmodelClass by viewModels()


    lateinit var adapter: ListAdapter

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val recyclerview = findViewById<RecyclerView>(R.id.recyclerview)
        val progress_circular = findViewById<ProgressBar>(R.id.progress_circular)

        val contraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()
        val periodicWorkRequest =
            PeriodicWorkRequest.Builder(SyncWork::class.java, Duration.ofMinutes(15))
                .setConstraints(contraints).build()
        WorkManager.getInstance(this.applicationContext).enqueue(periodicWorkRequest)

        recyclerview.layoutManager = LinearLayoutManager(this)
        if (isconnect()) {
            viewmodelClass.ge3tApiData(
                apiService,
                "search/repositories",
                "stars",
                "desc",
                "android",
                "20"
            )
        } else {
            viewmodelClass.getDBData(apiService)
            viewmodelClass.items.observe(this, {
                if (viewmodelClass.currentPageDB == 1) {
                    adapter = it!!.let { ListAdapter(it as ArrayList<Item>, this) }
                    recyclerview.adapter = adapter
                } else {
                    it?.let { it1 -> adapter.addtolist(it1) }
                }
            })
        }



        viewmodelClass.responseStatus.observe(this) {
            when (it.status) {
                Status.LOADING -> {
                    progress_circular.visibility= View.VISIBLE
                }
                Status.SUCCESS -> {
                    progress_circular.visibility= View.GONE
                    if (viewmodelClass.currentPage == 1) {
                        adapter =
                            viewmodelClass.items.value!!.let {
                                ListAdapter(
                                    it as ArrayList<Item>,
                                    this
                                )
                            }
                        recyclerview.adapter = adapter
                    } else {
                        viewmodelClass.items.value?.let { it1 -> adapter.addtolist(it1) }
                    }
                }
                Status.ERROR -> {
                    progress_circular.visibility= View.GONE
                }
            }
        }

        recyclerview.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val position =
                    (recyclerView.layoutManager as LinearLayoutManager).findLastCompletelyVisibleItemPosition()
                if (position + 1 == viewmodelClass.items.value?.size) {

                }
            }
        })
        recyclerview.addOnScrollListener(object : OnScroll(recyclerview) {
            override fun loadMore() {
                if (isconnect()) {
                    viewmodelClass.ge3tApiData(
                        apiService,
                        "search/repositories",
                        "stars",
                        "desc",
                        "android",
                        "20"
                    )
                } else {
                    viewmodelClass.getDBData(apiService)
                }
            }
        })
    }

    fun isconnect(): Boolean {
        val cm =
            applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val nInfo = cm.activeNetworkInfo
        val connected = nInfo != null && nInfo.isAvailable && nInfo.isConnected
        return connected
    }


}
