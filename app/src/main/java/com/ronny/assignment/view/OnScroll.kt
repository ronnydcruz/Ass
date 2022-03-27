package com.ronny.assignment.view

import android.widget.AbsListView.OnScrollListener.SCROLL_STATE_IDLE
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

abstract class OnScroll(recyclerView: RecyclerView?) : RecyclerView.OnScrollListener() {
    private val threshold = 2
    private var endWithAuto = false

    private var layoutManager: RecyclerView.LayoutManager? = null

    init {
        recyclerView!!.addOnScrollListener(this)
        layoutManager = recyclerView!!.layoutManager
    }


    override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
        super.onScrollStateChanged(recyclerView, newState)
        if (newState == SCROLL_STATE_IDLE) {
            val visibleItemCount = layoutManager!!.childCount
            val totalItemCount = layoutManager!!.itemCount
            var firstVisibleItemPosition = 0
                firstVisibleItemPosition =
                    (layoutManager as LinearLayoutManager?)!!.findLastVisibleItemPosition()
            if (visibleItemCount + firstVisibleItemPosition + threshold >= totalItemCount) {
                if (!endWithAuto) {
                    endWithAuto = true
                    loadMore()
                }
            } else {
                endWithAuto = false
            }
        }
    }


    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
    }
    abstract fun loadMore()
}